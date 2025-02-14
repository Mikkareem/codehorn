aws s3 mb s3://temp-for-s3
aws s3 cp . s3://temp-for-s3/project --recursive --exclude "*/build/**" --exclude ".git*" --exclude ".gradle/**" --exclude ".DS_Store" --exclude ".idea/**" --exclude "terraform/local-cloud-deployment/**"

cd terraform/local-cloud-deployment || exit

terraform init

terraform validate

terraform apply -auto-approve

cd ../..

aws s3 rb s3://temp-for-s3 --force

cd terraform/local-cloud-deployment && terraform destroy -auto-approve && cd ../../