# Security group for EC2 Instance Connect Endpoint

resource "aws_security_group" "eice_security_group" {
  name        = "${var.project_name}-EICE-SG"
  description = "Outbound SSH to VPC CIDR"
  vpc_id      = aws_vpc.vpc.id

  egress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = [var.vpc_cidr]
  }

  tags = {
    Name = "${var.project_name}-EICE-SG"
  }
}


# Security group for Application Load Balancer

resource "aws_security_group" "alb_security_group" {
  name        = "${var.project_name}-ALB-SG"
  description = "HTTP/HTTPS from internet"
  vpc_id      = aws_vpc.vpc.id

  ingress {
    description = "HTTP"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "HTTPS"
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    description = "Allow all outbound traffic"
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "${var.project_name}-ALB-SG"
  }
}

# Security group for application servers (ECS, EC2)

resource "aws_security_group" "app_server_security_group" {
  name        = "${var.environment}-${var.project_name}-WEB-SG"
  description = "HTTP/HTTPS from ALB, SSH from EICE"
  vpc_id      = aws_vpc.vpc.id

  ingress {
    description     = "SSH from EICE"
    from_port       = 22
    to_port         = 22
    protocol        = "tcp"
    security_groups = [aws_security_group.eice_security_group.id]
  }

  ingress {
    description     = "HTTP from ALB"
    from_port       = 80
    to_port         = 80
    protocol        = "tcp"
    security_groups = [aws_security_group.alb_security_group.id]
  }

  ingress {
    description     = "HTTPS from ALB"
    from_port       = 443
    to_port         = 443
    protocol        = "tcp"
    security_groups = [aws_security_group.alb_security_group.id]
  }

  egress {
    description = "Allow all outbound traffic"
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "${var.project_name}-AppServ-SG"
  }
}


# Security group for data migration server

resource "aws_security_group" "db_migrate_server_security_group" {
  name        = "${var.project_name}-DB-Migrate-Server-SG"
  description = "SSH from EICE"
  vpc_id      = aws_vpc.vpc.id

  ingress {
    description     = "SSH from EICE"
    from_port       = 22
    to_port         = 22
    protocol        = "tcp"
    security_groups = [aws_security_group.eice_security_group.id]
  }

  egress {
    description = "Allow all outbound traffic"
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "${var.project_name}-MigServ-SG"
  }
}


# Security group for database (RDS, Aurora)

resource "aws_security_group" "database_security_group" {
  name        = "${var.project_name}-Database-SG"
  description = "MySQL/Aurora from app and migration servers"
  vpc_id      = aws_vpc.vpc.id

  ingress {
    description     = "MySQL/Aurora from app servers"
    from_port       = 3306
    to_port         = 3306
    protocol        = "tcp"
    security_groups = [aws_security_group.app_server_security_group.id]
  }

  ingress {
    description     = "MySQL/Aurora from migration server"
    from_port       = 3306
    to_port         = 3306
    protocol        = "tcp"
    security_groups = [aws_security_group.db_migrate_server_security_group.id]
  }

  egress {
    description = "Allow all outbound traffic"
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "${var.project_name}-DB-SG"
  }
}