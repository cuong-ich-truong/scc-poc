resource "aws_s3_bucket" "s3_bucket_scc" {
  bucket = "scc-s3-bucket"
}

resource "aws_s3_object" "s3_bucket_web_scc" {
  bucket = aws_s3_bucket.s3_bucket_scc.id
  key = "web-dev"
  source = "../target/web-dev.jar"
}

resource "aws_elastic_beanstalk_application" "web_scc_app" {
  name = "web-scc-app"
  description = "SCC Web Application"
}


resource "aws_elastic_beanstalk_application_version" "web_scc_version" {
  application = aws_elastic_beanstalk_application.web_scc_app.name
  bucket = aws_s3_bucket.s3_bucket_scc.id
  key = aws_s3_object.s3_bucket_web_scc.id
  name = "myapp-1.0.0"
}

resource "aws_elastic_beanstalk_environment" "web_scc_env" {
  name = "web-scc-env"
  application = aws_elastic_beanstalk_application.web_scc_app.name
  solution_stack_name = "64bit Amazon Linux 2 v3.4.10 running Corretto 17"
  version_label = aws_elastic_beanstalk_application_version.web_scc_version.name

  setting {
    name = "SERVER_PORT"
    namespace = "aws:elasticbeanstalk:application:environment"
    value = "5000"
  }

  setting {
    namespace = "aws:ec2:instances"
    name      = "InstanceTypes"
    value     = "t2.micro"
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name = "IamInstanceProfile"
    value = "aws-elasticbeanstalk-ec2-role"
  }
}





