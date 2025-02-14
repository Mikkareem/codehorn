terraform {
  required_version = ">= 1.0.0" # Ensure that the Terraform version is 1.0.0 or higher

  required_providers {
    aws = {
      source = "hashicorp/aws" # Specify the source of the AWS provider
      version = "~> 4.0"        # Use a version of the AWS provider that is compatible with version
    }
  }
}

provider "aws" {
  region = "ap-south-1"
}

variable "key_pair_name" {
  type = string
  default = "NajimaBanuKeyPair"
}

resource "aws_security_group" "allow_ssh" {
  name        = "allow_ssh"
  description = "Allow SSH access"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]  # Allows access from any IP (for testing purposes, but restrict to your IP for security)
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]  # Allow outbound traffic to any IP
  }

  tags = {
    Name = "allow_ssh_sg"
  }
}

# DELIVERY SERVICE SETUP START

resource "aws_security_group" "service_sg" {
  name        = "delivery_sg"
  description = "Allow HTTP and forwarded traffic for Delivery Service"

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}


resource "aws_iam_role" "ec2_s3_role" {
  name = "EC2_S3_Access_Role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Action = "sts:AssumeRole"
      Effect = "Allow"
      Principal = {
        Service = "ec2.amazonaws.com"
      }
    }]
  })
}

resource "aws_iam_policy_attachment" "s3_readonly_attachment" {
  name       = "s3_readonly_attachment"
  roles      = [aws_iam_role.ec2_s3_role.name]
  policy_arn = "arn:aws:iam::aws:policy/AmazonS3ReadOnlyAccess"
}

resource "aws_iam_instance_profile" "ec2_s3_profile" {
  name = "EC2_S3_Instance_Profile"
  role = aws_iam_role.ec2_s3_role.name
}

resource "aws_instance" "aws_delivery_service_instance" {
  ami = "ami-0c50b6f7dc3701ddd"
  instance_type = "t3.micro"
  key_name = var.key_pair_name

  security_groups = [aws_security_group.allow_ssh.name, aws_security_group.service_sg.name]

  iam_instance_profile = aws_iam_instance_profile.ec2_s3_profile.name  # Attach IAM role

  # user_data = <<-EOF
  #   #!/bin/bash
  #   sudo yum update -y
  #   sudo yum install -y java-17-openjdk
  # EOF

  tags = {
    Name = "DeliveryServiceInstance" # Tag the instance with a Name tag for easier identification
  }

  provisioner "remote-exec" {
    connection {
      type = "ssh"
      host = self.public_ip
      user = "ec2-user"
      private_key = file("~/DEV/subordinates/SSHKeys/${var.key_pair_name}.pem")
    }

    inline = [
      "echo 'Setup of Delivery Service, Starting....'",
      "sudo yum update -y",
      "sudo yum install -y java-17-amazon-corretto-devel",
      "cd /home/ec2-user",
      "export JAVA_HOME=/usr/lib/jvm/java-17-amazon-corretto.x86_64",
      "echo $JAVA_HOME",
      "aws s3 cp s3://temp-for-s3/project project --recursive",
      "cd project",
      "cat <<EOF > ./gradle/wrapper/gradle-wrapper.properties",
      "distributionBase=GRADLE_USER_HOME",
      "distributionPath=wrapper/dists",
      "distributionUrl=https\\://services.gradle.org/distributions/gradle-8.11-bin.zip",
      "networkTimeout=10000",
      "validateDistributionUrl=true",
      "zipStoreBase=GRADLE_USER_HOME",
      "zipStorePath=wrapper/dists",
      "EOF",
      "cat ./gradle/wrapper/gradle-wrapper.properties",
      "chmod +x gradlew",
      "./gradlew build",
      # "nohup java -jar /home/ec2-user/project/delivery-1.0.0.jar > /home/ec2-user/app.log 2>&1 &",
      "echo 'Setup of Delivery Service, Stopped'",
    ]
  }
}