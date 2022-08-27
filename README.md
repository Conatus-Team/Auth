# 프로젝트 이름
프로젝트 간단 소개

## 프로젝트 목적
<!--
프로젝트에 대해 간단하게 설명하는 내용을 포함하는 것이 좋다.

이 프로젝트는 무엇을 위한 것인가
어떤 문제를 해결할 수 있는가
왜 이 프로젝트가 유용한가
어떤 사람들이 이 프로젝트를 사용하면 좋은가
이 프로젝트는 어떻게 작동하는가
-->


## 기능 설명

<!--
## 결과 화면
-->

## 사용 기술
<!-- 기술 스택 -->


## 실행/설치 방법
<!--
프로젝트를 설치, 사용하기 위해 필요한 전제조건이 있는가
어떻게 설치, 사용, 테스트하는가
설치 가이드 문서는 어디에 있는가
-->

<!--
## 저작권/라이선스

어떤 라이선스로 배포되는가?
상세한 라이선스 정보는 어디에서 확인할 수 있는가
프로젝트를 사용함에 있어 제약 조건이 있는가(특허, 상업적 사용)
-->

<!--
## 외부 리소스 정보

프로젝트 내에 포함된 외부의 코드나 리소스의 정보
각각의 출처 및 배포 라이선스는 무엇인가
-->

<!--
## 이슈사항
버그 등-->



# Auth

## Running in local development environment

```
mvn spring-boot:run
```

## Packaging and Running in docker environment

```
mvn package -B
docker build -t username/Auth:v1 .
docker run username/Auth:v1
```

## Push images and running in Kubernetes

```
docker login 
# in case of docker hub, enter your username and password

docker push username/Auth:v1
```

Edit the deployment.yaml under the /kubernetes directory:
```
    spec:
      containers:
        - name: Auth
          image: username/Auth:latest   # change this image name
          ports:
            - containerPort: 8080

```

Apply the yaml to the Kubernetes:
```
kubectl apply -f kubernetes/deployment.yml
```

See the pod status:
```
kubectl get pods -l app=Auth
```

If you have no problem, you can connect to the service by opening a proxy between your local and the kubernetes by using this command:
```
# new terminal
kubectl port-forward deploy/Auth 8080:8080

# another terminal
http localhost:8080
```

If you have any problem on running the pod, you can find the reason by hitting this:
```
kubectl logs -l app=Auth
```

Following problems may be occurred:

1. ImgPullBackOff:  Kubernetes failed to pull the image with the image name you've specified at the deployment.yaml. Please check your image name and ensure you have pushed the image properly.
1. CrashLoopBackOff: The spring application is not running properly. If you didn't provide the kafka installation on the kubernetes, the application may crash. Please install kafka firstly:

https://labs.msaez.io/#/courses/cna-full/full-course-cna/ops-utility

