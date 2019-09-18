# hire_yourself

##

create files **docker-compose.yml** from __dist__ file 

create files **src/environments/environment.ts** from __dist__ file 

create files **src/environments/environment.prod.ts** from __dist__ file 

configure your environment files 

##

```
docker network create \
        --subnet=172.38.0.0/24 \
        --ip-range=172.38.0.0/24 \
        --gateway=172.38.0.1 \
        hirey-network

docker-compose up
```



##
### Access
access pages via [172.38.0.2:4200](https://172.38.0.2:4200) or [localhost:4200](https://localhost:4200)