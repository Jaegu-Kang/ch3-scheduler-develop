# 일정 관리 앱 만들기(숙련)

## 프로젝트 개요
Spring Boot와 JPA를 기반으로 구현된 간단한 일정 관리 앱만들기.

## 기능 구현 내용
일정을 등록, 조회, 수정, 삭제할 수 있다.
수정과 삭제는 비밀번호 검증을 통해 이루어집니다.

##  기술 스택
- **Language:** Java
- **Framework:** Spring Boot
- **Data:** Spring Data JPA
- **Database:** MySQL

---
## API 명세서

### 1. 일정 생성

- **Method:** POST
- **URL:** /api/schedules
- **Description:** 새로운 일정을 생성합니다.
- **Request:**

  | 필드명        | 타입 | 필수 여부 | 설명                 |
  |:-----------| :--- | :---: |:-------------------|
  | `name`     | String | O | 작성자 이름             |
  | `title`    | String | O | 할일 제목              |
  | `content`  | String | O | 할일 내용              |
  | `password` | String | O | 비밀번호 (수정/삭제 시 검증용) |

```json
{
  "name": "강재구",
  "title": "과제작성",
  "content": "과제를 해야한다.",
  "password": "12345678"
}
```
- **Response:**

  | 필드명           | 타입 | 필수 여부 | 설명                 |
  |:--------------| :--- | :---: |:-------------------|
  | `id`          | Long | O | 일정 고유 식별자          |
  | `name`        | String | O | 작성자 이름             |
  | `title`       | String | O | 할일 제목              |
  | `content`     | String | O | 할일 내용              |
  | `createdAt`   | String | O | 작성일 (JPA Auditing) |
  | `motifieddAt` | String | O | 수정일 (JPA Auditing) |

```json
{
  "id": 1,
  "name": "강재구",
  "title": "과제작성",
  "content":"과제를 해야한다.",
  "createdAt": "2026-04-16T17:30:30",
  "motifiedAt": "2026-04-16T17:30:30"
}
```
### 2. 일정 조회

- **Method:** GET
- **URL:** /api/schedules
- **Description:** 등록된 모든 일정을 배열 형태로 조회합니다.
- **Request:** 없음
- **Response:**

  | 필드명              | 타입 | 필수 여부 | 설명                 |
  |:-----------------| :--- | :---: |:-------------------|
  | `[].id`          | Long | O | 일정 고유 식별자          |
  | `[].name`        | String | O | 작성자 이름             |
  | `[].title`       | String | O | 할일 제목              |
  | `[].content`     | String | O | 할일 내용              |
  | `[].createdAt`   | String | O | 작성일 (JPA Auditing) |
  | `[].motifieddAt` | String | O | 수정일 (JPA Auditing) |

```json
[{
  "id": 1,
  "name": "강재구",
  "title": "과제작성",
  "content":"과제를 해야한다.",
  "createdAt": "2026-04-16T17:30:30",
  "motifiedAt": "2026-04-16T17:30:30"
}
]
```

### 3. 일정 단건 조회

- **Method:** GET
- **URL:** /api/schedules/{id}
- **Description:**  특정 ID의 일정을 상세 조회합니다.
- **Request:** Path Variable {id} (api/schedules/1) 
  - id (Long): 조회할 일정의 고유 ID
- **Response:**

  | 필드명              | 타입 | 필수 여부 | 설명                 |
    |:-----------------| :--- | :---: |:-------------------|
  | `[].id`          | Long | O | 일정 고유 식별자          |
  | `[].name`        | String | O | 작성자 이름             |
  | `[].title`       | String | O | 할일 제목              |
  | `[].content`     | String | O | 할일 내용              |
  | `[].createdAt`   | String | O | 작성일 (JPA Auditing) |
  | `[].motifieddAt` | String | O | 수정일 (JPA Auditing) |

```json
{
  "id": 1,
  "name": "강재구",
  "title": "과제작성",
  "content":"과제를 해야한다.",
  "createdAt": "2026-04-16T17:30:30",
  "motifiedAt": "2026-04-16T17:30:30"
}
```

### 4. 일정 수정

- **Method:** PUT
- **URL:** /api/schedules/{id}
- **Description:** 선택한 일정을 수정합니다.(비밀번호 일치 시에만)
- **Request:**

  | 필드명        | 타입 | 필수 여부 | 설명              |
    |:-----------| :--- |:-----:|:----------------|
  | `name`     | String |   X   | 작성자 이름          |
  | `title`    | String |   X   | 할일 제목           |
  | `content`  | String |   X   | 할일 내용           |
  | `password` | String |   O   | 비밀번호 (삭제 시 검증용) |

```json
{
  "name": "강재구",
  "title": "과제작성중",
  "content": "Lv 0. 과제 작성중.",
  "password": "12345678"
}
```
- **Response:**

  | 필드명           | 타입 | 필수 여부 | 설명                 |
    |:--------------| :--- |:-----:|:-------------------|
  | `id`          | Long |   O   | 일정 고유 식별자          |
  | `name`        | String |   X   | 작성자 이름             |
  | `title`       | String |   X   | 할일 제목              |
  | `content`     | String |   X   | 할일 내용              |
  | `createdAt`   | String |   O   | 작성일 (JPA Auditing) |
  | `motifieddAt` | String |   O   | 수정일 (JPA Auditing) |

```json
{
  "id": 1,
  "name": "강재구",
  "title": "과제작성중",
  "content":"Lv 0. 과제 작성중.",
  "createdAt": "2026-04-16T17:30:30",
  "motifiedAt": "2026-04-16T17:40:30"
}
```

### 5. 일정 삭제

- **Method:** DELETE
- **URL:** /api/schedules/{id}
- **Description:** 선택한 일정을 삭제합니다.(비밀번호 일치 시에만)
- **Request:** Path Variable {id} (api/schedules/1),Request Body

  | 필드명        | 타입 | 필수 여부 | 설명              |
      |:-----------| :--- |:-----:|:----------------|
  | `password` | String |   O   | 비밀번호 (삭제 시 검증용) |

```json
{
  "password": "12345678"
}
```
- **Response:** 본문 없음
