sudo yum install docker
sudo systemctl start docker
sudo systemctl status docker #active running 확인
sudo curl -L "https://github.com/docker/compose/releases/download/v2.32.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

docker-compose version

#docker-compose up -d local-grafana
#-d 백그라운드로 실행

# sudo 안쓰게하기
sudo usermod -aG docker ec2-user
newgrp docker
docker ps