# 일정 관리 앱 만들기(숙련)

## 프로젝트 개요
Spring Boot와 JPA를 기반으로 구현된 간단한 일정 관리 앱만들기.

## 기능 구현 내용
일정을 등록, 전체조회, 단건조회, 수정, 삭제할 수 있다.
유저를 등록 전체조회, 단건조회, 수정, 삭제할 수 있다.

##  기술 스택
- **Language:** Java
- **Framework:** Spring Boot
- **Data:** Spring Data JPA
- **Database:** MySQL

---
## API 명세서

## Lv1 일정 CRUD API 명세서

### 1. 일정 생성

- **Method:** POST
- **URL:** /api/schedulers
- **Description:** 새로운 일정을 생성합니다.
- 🩹fix(유저와의 연관관계를 위해 기존 name 필드 삭제,userId 필드로 수정)
- **Request:**

  | 필드명       | 타입     | 필수 여부 | 설명        |
  |:----------|:-------|:-----:|:----------|
  | `userId`  | Long   |   O   | 유저 고유 식별자 |
  | `title`   | String |   O   | 할일 제목     |
  | `content` | String |   O   | 할일 내용     |

```json
{
  "userId": 1,
  "title": "과제작성",
  "content": "과제를 해야한다."
}
```
- **Response:**

  | 필드명           | 타입     | 필수 여부 | 설명                 |
  |:--------------|:-------|:-----:|:-------------------|
  | `id`          | Long   |   O   | 일정 고유 식별자          |
  | `userId`      | Long   |   O   | 유저 고유 식별자          |
  | `title`       | String |   O   | 할일 제목              |
  | `content`     | String |   O   | 할일 내용              |
  | `createdAt`   | String |   O   | 작성일 (JPA Auditing) |
  | `modifieddAt` | String |   O   | 수정일 (JPA Auditing) |

```json
{
  "id": 1,
  "userId": 1,
  "title": "과제작성",
  "content":"과제를 해야한다.",
  "createdAt": "2026-04-16T17:30:30",
  "modifiedAt": "2026-04-16T17:30:30"
}
```
### 2. 일정 전체 조회

- **Method:** GET
- **URL:** /api/schedulers
- **Description:** 등록된 모든 일정을 배열 형태로 조회합니다.
- 🩹fix(유저와의 연관관계에 의해 기존 name 필드에서,userId 필드가 반환)
- **Request:** 없음
- **Response:**

  | 필드명              | 타입     | 필수 여부 | 설명                 |
  |:-----------------|:-------|:-----:|:-------------------|
  | `[].id`          | Long   |   O   | 일정 고유 식별자          |
  | `[].userId`      | Long   |   O   | 유저 고유 식별자          |
  | `[].title`       | String |   O   | 할일 제목              |
  | `[].content`     | String |   O   | 할일 내용              |
  | `[].createdAt`   | String |   O   | 작성일 (JPA Auditing) |
  | `[].modifieddAt` | String |   O   | 수정일 (JPA Auditing) |

```json
[
{
  "id": 1,
  "userId": 1,
  "title": "과제작성",
  "content":"과제를 해야한다.",
  "createdAt": "2026-04-16T17:30:30",
  "modifiedAt": "2026-04-16T17:30:30"
}
]
```

### 3. 일정 단건 조회

- **Method:** GET
- **URL:** /api/schedulers/{id}
- **Description:**  특정 ID의 일정을 상세 조회합니다.
- 🩹fix(유저와의 연관관계에 의해 기존 name 필드에서,userId 필드가 반환)
- **Request:** Path Variable {id} (api/schedules/1) 
  - id (Long): 조회할 일정의 고유 ID
- **Response:**

  | 필드명           | 타입     | 필수 여부 | 설명                 |
    |:--------------|:-------|:-----:|:-------------------|
  | `id`          | Long   |   O   | 일정 고유 식별자          |
  | `userId`      | Long   |   O   | 유저 고유 식별자          |
  | `title`       | String |   O   | 할일 제목              |
  | `content`     | String |   O   | 할일 내용              |
  | `createdAt`   | String |   O   | 작성일 (JPA Auditing) |
  | `modifieddAt` | String |   O   | 수정일 (JPA Auditing) |

```json
{
  "id": 1,
  "userId": 1,
  "title": "과제작성",
  "content":"과제를 해야한다.",
  "createdAt": "2026-04-16T17:30:30",
  "modifiedAt": "2026-04-16T17:30:30"
}
```

### 4. 일정 수정

- **Method:** PUT
- **URL:** /api/schedulers/{id}
- **Description:** 선택한 일정을 수정합니다.
- 🩹fix(유저와의 연관관계에 의해 기존 name 필드 삭제)
- **Request:**

  | 필드명       | 타입     | 필수 여부 | 설명     |
    |:----------|:-------|:-----:|:-------|
  | `title`   | String |   X   | 수정할 제목 |
  | `content` | String |   X   | 수정할 내용 |

```json
{
  "name": "강재구",
  "title": "과제작성중",
  "content": "Lv 0. 과제 작성중."
}
```
- **Response:**

  | 필드명           | 타입     | 필수 여부 | 설명                 |
    |:--------------|:-------|:-----:|:-------------------|
  | `id`          | Long   |   O   | 일정 고유 식별자          |
  | `userId`      | Long   |   O   | 작성자 고유 식별자         |
  | `title`       | String |   X   | 수정된 제목             |
  | `content`     | String |   X   | 수정된 내용             |
  | `createdAt`   | String |   O   | 작성일 (JPA Auditing) |
  | `modifieddAt` | String |   O   | 수정일 (JPA Auditing) |

```json
{
  "id": 1,
  "userId": 1,
  "title": "과제작성중",
  "content":"Lv 0. 과제 작성중.",
  "createdAt": "2026-04-16T17:30:30",
  "modifiedAt": "2026-04-16T17:40:30"
}
```

### 5. 일정 삭제

- **Method:** DELETE
- **URL:** /api/schedulers/{id}
- **Description:** 선택한 일정을 삭제합니다.
- **Request:** Path Variable {id} (api/schedules/1)
- **Response:** 본문 없음

## Lv2 유저 CRUD API 명세서

### 1. 유저 생성

- **Method:** POST
- **URL:** /api/users
- **Description:** 새로운 유저를 생성합니다.
- **Request:**

  | 필드명     | 타입     | 필수 여부 | 설명     |
  |:--------|:-------|:-----:|:-------|
  | `name`  | String |   O   | 유저 이름  |
  | `email` | String |   O   | 유저 이메일 |
  

```json
{
  "name": "강재구",
  "email": "rkdworn8@gmail.com"
}
```
- **Response:**

  | 필드명           | 타입     | 필수 여부 | 설명                 |
    |:--------------|:-------|:-----:|:-------------------|
  | `id`          | Long   |   O   | 유저 고유 식별자          |
  | `name`        | String |   O   | 유저 이름              |
  | `title`       | String |   O   | 유저 이메일             |
  | `createdAt`   | String |   O   | 작성일 (JPA Auditing) |
  | `modifieddAt` | String |   O   | 수정일 (JPA Auditing) |

```json
{
  "id": 1,
  "name": "강재구",
  "email": "rkdworn@gmail.com",
  "createdAt": "2026-04-16T17:30:30",
  "modifiedAt": "2026-04-16T17:30:30"
}
```
### 2. 유저 전체 조회

- **Method:** GET
- **URL:** /api/users
- **Description:** 등록된 모든 유저를 배열 형태로 조회합니다.
- **Request:** 없음
- **Response:**

  | 필드명              | 타입     | 필수 여부 | 설명                 |
    |:-----------------|:-------|:-----:|:-------------------|
  | `[].id`          | Long   |   O   | 유저 고유 식별자          |
  | `[].name`        | String |   O   | 유저 이름              |
  | `[].title`       | String |   O   | 유저 이메일             |
  | `[].createdAt`   | String |   O   | 작성일 (JPA Auditing) |
  | `[].modifieddAt` | String |   O   | 수정일 (JPA Auditing) |

```json
[
{
  "id": 1,
  "name": "강재구",
  "email": "rkdworn@gmail.com",
  "createdAt": "2026-04-16T17:30:30",
  "modifiedAt": "2026-04-16T17:30:30"
}
]
```

### 3. 유저 단건 조회

- **Method:** GET
- **URL:** /api/users/{id}
- **Description:**  특정 ID의 유저를 상세 조회합니다.
- **Request:** Path Variable {id} (api/users/1)
  - id (Long): 조회할 유저의 고유 ID
- **Response:**

  | 필드명           | 타입     | 필수 여부 | 설명                 |
      |:--------------|:-------|:-----:|:-------------------|
  | `id`          | Long   |   O   | 유저 고유 식별자          |
  | `name`        | String |   O   | 유저 이름              |
  | `email`       | String |   O   | 유저 이메일             |
  | `createdAt`   | String |   O   | 작성일 (JPA Auditing) |
  | `modifieddAt` | String |   O   | 수정일 (JPA Auditing) |

```json
{
  "id": 1,
  "name": "강재구",
  "email": "rkdworn@gmail.com",
  "createdAt": "2026-04-16T17:30:30",
  "modifiedAt": "2026-04-16T17:30:30"
}
```

### 4. 유저 수정

- **Method:** PUT
- **URL:** /api/users/{id}
- **Description:** 선택한 유저의 정보를 수정합니다.
- **Request:**

  | 필드명     | 타입     | 필수 여부 | 설명         |
    |:--------|:-------|:-----:|:-----------|
  | `name`  | String |   O   | 수정할 유저 이름  |
  | `email` | String |   O   | 수정할 유저 이메일 |

```json
{
  "name": "강재구MK2",
  "email": "rkdwornmk2@gmail.com"
}
```
- **Response:**

  | 필드명           | 타입     | 필수 여부 | 설명                 |
        |:--------------|:-------|:-----:|:-------------------|
  | `id`          | Long   |   O   | 유저 고유 식별자          |
  | `name`        | String |   O   | 수정된 유저 이름          |
  | `email`       | String |   O   | 수정된 유저 이메일         |
  | `createdAt`   | String |   O   | 작성일 (JPA Auditing) |
  | `modifieddAt` | String |   O   | 수정일 (JPA Auditing) |

```json
{
  "id": 1,
  "name": "강재구MK2",
  "email": "rkdwornmk2@gmail.com",
  "createdAt": "2026-04-16T17:30:30",
  "modifiedAt": "2026-04-16T18:30:30"
}
```

### 5. 유저 삭제

- **Method:** DELETE
- **URL:** /api/users/{id}
- **Description:** 선택한 유저를 삭제합니다.
- **Request:** Path Variable {id} (api/users/1)
- **Response:** 본문 없음
