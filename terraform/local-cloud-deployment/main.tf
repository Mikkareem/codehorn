# terraform {
#   required_version = ">= 1.0.0" # Ensure that the Terraform version is 1.0.0 or higher
#
#   required_providers {
#     aws = {
#       source = "hashicorp/aws" # Specify the source of the AWS provider
#       version = "~> 4.0"        # Use a version of the AWS provider that is compatible with version
#     }
#   }
# }

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

variable "app-version" {
  type = string
  default = "1.0.0"
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

#
# resource "aws_iam_role" "ec2_s3_role" {
#   name = "EC2_S3_Access_Role"
#
#   assume_role_policy = jsonencode({
#     Version = "2012-10-17"
#     Statement = [{
#       Action = "sts:AssumeRole"
#       Effect = "Allow"
#       Principal = {
#         Service = "ec2.amazonaws.com"
#       }
#     }]
#   })
# }
#
# resource "aws_iam_policy_attachment" "s3_readonly_attachment" {
#   name       = "s3_readonly_attachment"
#   roles      = [aws_iam_role.ec2_s3_role.name]
#   policy_arn = "arn:aws:iam::aws:policy/AmazonS3ReadOnlyAccess"
# }
#
# resource "aws_iam_instance_profile" "ec2_s3_profile" {
#   name = "EC2_S3_Instance_Profile"
#   role = aws_iam_role.ec2_s3_role.name
# }
#
# resource "aws_instance" "aws_delivery_service_instance" {
#   ami = "ami-0c50b6f7dc3701ddd"
#   instance_type = "t3.micro"
#   key_name = var.key_pair_name
#
#   security_groups = [aws_security_group.allow_ssh.name, aws_security_group.service_sg.name]
#
#   iam_instance_profile = aws_iam_instance_profile.ec2_s3_profile.name  # Attach IAM role
#
#   tags = {
#     Name = "JavaExecutionServiceInstance"
#   }
#
#   provisioner "remote-exec" {
#     connection {
#       type = "ssh"
#       host = self.public_ip
#       user = "ec2-user"
#       private_key = file("${var.key_pair_name}.pem")
#     }
#
#     inline = [
#       "echo 'Setup of Java Execution Service, Starting....'",
#       "sudo yum update -y",
#       "sudo yum install -y java-17-amazon-corretto",
#       "cd /home/ec2-user",
#       "aws s3 cp s3://${var.sources-bucket-name}/java-execution-service-${var.app-version}.jar app.jar",
#       "nohup java -jar /home/ec2-user/app.jar > /home/ec2-user/app.log 2>&1 &",
#       "echo 'Setup of Java Execution Service, Stopped'",
#     ]
#   }
# }