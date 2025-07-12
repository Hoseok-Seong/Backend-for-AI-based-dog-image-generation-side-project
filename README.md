
# 🐶 Backend-for-AI-based-dog-image-generation-side-project

AI 기반 반려견 이미지 생성 서비스를 위한 백엔드 시스템입니다.  
Stable Diffusion 기반 이미지 생성을 위해 사용자 입력을 분석하고 프롬프트를 생성하며,  
이미지 유효성 검증, S3 업로드/삭제, Redis 세션 관리, 보안 인증까지 포함하는 **전체 백엔드 인프라를 구축**하였습니다.

---

## 🚀 프로젝트 개요

이 프로젝트는 사용자로부터 반려견 사진과 설명 정보를 입력받아, 해당 정보를 기반으로 Stable Diffusion을 통해  
AI 이미지 생성 요청을 수행하고, 생성된 이미지를 저장/반환하는 역할을 담당하는 **AI 이미지 생성 파이프라인의 백엔드 시스템**입니다.

---

## ✅ 주요 기능

- 반려견 사진 + 사용자 입력 기반 프롬프트 생성
- Stable Diffusion 서버와 연동하여 AI 이미지 생성
- 이미지 유효성 검사 (MIME type, 크기 제한 등)
- S3에 원본 이미지 업로드 및 삭제 처리
- JWT 기반 인증 + Redis 기반 세션 관리
- 사용자 구독 상태 검증 로직 내장

---

## 🛠 기술 스택

| 분류 | 기술 |
|------|------|
| Language | Java |
| Framework | Spring Boot, Spring Security, Spring Data JPA |
| AI 연동 | Stable Diffusion (외부 모델 호출) |
| Storage | AWS S3, Redis, MariaDB |
| 인증/보안 | JWT (java-jwt), Spring Security |
| 기타 | Tika, JSON, Commons IO |
| DevOps | GitHub Actions, Docker, EC2 배포 자동화

---

## ⚙️ 배포 파이프라인

- GitHub Actions 기반 CI/CD 구성
- `main` 브랜치 push 시 다음 수행:
  1. Gradle 빌드 → Docker 이미지 생성
  2. DockerHub 이미지 푸시
  3. EC2 원격 접속 → 기존 컨테이너 제거 및 최신 이미지로 재배포
  4. `.env.production` 자동 생성 및 환경변수 주입

---

## 💻 주요 엔드포인트

```http
POST /api/models-lab/images
  - 이미지 업로드 + 사용자 설명 기반 AI 이미지 생성 요청

POST /api/picture-create
  - 사용자 맞춤 UI 생성 데이터를 제공
```

---

## 👨‍💻 담당 역할

- 전체 백엔드 시스템 설계 및 API 개발
- 프롬프트 생성 로직 설계 (`PromptUtil`)
- AI 모델 서버 연동 및 결과 처리
- 이미지 유효성 검사 및 오류 대응 처리
- CI/CD 자동화 (GitHub Actions + Docker + EC2)
- 보안 정책 수립 (JWT, Redis, 인증 흐름)

---

## 📎 프롬프트 예시 (PromptUtil)

```txt
"Turn the dog into a magical fantasy creature. The dog is a small poodle with a cheerful expression. Preserve over 90% of the dog's features while incorporating elements of a fantasy world."
```

사용자 입력(품종, 털 색, 표정 등)을 바탕으로 자동 프롬프트 생성

---

## 📝 트러블슈팅 & 인사이트

- 이미지 MIME 타입 식별을 위해 Apache Tika 사용
- 10MB 이상 이미지 업로드 차단 로직
- S3 업로드 후 즉시 삭제 및 키 관리로 스토리지 비용 최적화
- 모델 응답 지연 및 실패 대비 재시도 전략 검토 중

---

## 📂 디렉토리 구조 (간략)

```
├── controller/
│   └── AIController.java
├── service/
│   └── ModelsLabFacadeService.java
├── util/
│   └── PromptUtil.java
```
