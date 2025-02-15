provider "aws" {
  region = "ap-south-1"
}

variable "key_pair_name" {
  type = string
  default = "NajimaBanuKeyPair"
}

variable "sources-bucket-name" {
  type = string
  default = "temp-sources-for-codehorn"
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

# Consul Instance Start

resource "aws_security_group" "consul_sg" {
  name        = "consul_sg"
  description = "Allow HTTP and forwarded traffic for Consul discovery Service"

  ingress {
    from_port   = 8500
    to_port     = 8500
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

resource "aws_instance" "aws_consul" {
    ami = "ami-0c50b6f7dc3701ddd"
    instance_type = "t2.micro"
    key_name = var.key_pair_name

    security_groups = [aws_security_group.allow_ssh.name, aws_security_group.consul_sg.name]

    user_data = <<-EOF
                #!/bin/bash
                sudo apt update -y
                sudo apt install -y unzip
                wget https://releases.hashicorp.com/consul/1.13.0/consul_1.13.0_linux_amd64.zip
                unzip consul_1.13.0_linux_amd64.zip
                sudo mv consul /usr/local/bin/
                consul agent -server -bootstrap -ui -data-dir=/tmp/consul -client=0.0.0.0 &
                EOF

    tags = {
      Name = "CodehornConsulInstance"
    }
}

# Consul Instance End

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

resource "aws_instance" "aws_java_exec_service_instance" {
  ami = "ami-0c50b6f7dc3701ddd"
  instance_type = "t2.micro"
  key_name = var.key_pair_name

  security_groups = [aws_security_group.allow_ssh.name, aws_security_group.service_sg.name]

  iam_instance_profile = aws_iam_instance_profile.ec2_s3_profile.name  # Attach IAM role

  user_data = <<-EOF
              #!/bin/bash
              echo "export CODEHORN_CONSUL_HOST=${aws_instance.aws_consul.public_ip}" >> /etc/environment
              source /etc/environment
              EOF

  tags = {
    Name = "CodehornJavaExecutionInstance"
  }

  provisioner "remote-exec" {
    connection {
      type = "ssh"
      host = self.public_ip
      user = "ec2-user"
      private_key = file("${var.key_pair_name}.pem")
    }

    inline = [
      "echo 'Setup of Java Execution Service, Starting....'",
      "echo Consul Host: $CODEHORN_CONSUL_HOST",
      "sudo yum update -y",
      "sudo yum install -y java-17-amazon-corretto",
      "cd /home/ec2-user",
      "touch app.log",
      "aws s3 cp s3://${var.sources-bucket-name}/java-execution-service.jar app.jar",
      "nohup java -jar /home/ec2-user/app.jar > /home/ec2-user/app.log 2>&1 &",
      "sleep 10",
      "echo 'Setup of Java Execution Service, Stopped'",
    ]
  }
}

output "consul_service_instance_ip" {
  value = aws_instance.aws_consul.public_ip
}

output "java_exec_service_instance_ip" {
  value = aws_instance.aws_java_exec_service_instance.public_ip
}