# hire_yourself

##

create files **docker-compose.yml** from __dist__ file 

create files **src/environments/environment.ts** from __dist__ file 

create files **src/environments/environment.prod.ts** from __dist__ file 
#
###Backend
create files **src/main/resources/application.properties** from __dist__ file 

#
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

##
##

### Useful links

1) [Api endpoints](https://docs.google.com/spreadsheets/d/1VXxShSliQx0vOeyRRCRZR6xITRyGK37wyzB9lwsKdAc/edit?usp=sharing)
2) [Doc specifications](https://drive.google.com/open?id=1eKqyzhiffXpzLL7itROobQrRCpgpAIsV)
3) [Trello dashboard](https://trello.com/b/YpSdcRcu/hitproject)
4) [Api_postmanCollection](https://www.getpostman.com/collections/2aaecac81f3b51cbded1)
