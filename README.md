# Fin-Point

## Commit Message Convention
- INIT : 새로운 branch 를 만들고 작업을 시작
- FEAT : 새로운 기능의 추가
- REFACTOR : 코드 리팩토링
- FIX : 오류 수정, 버그 FIX
- CONFIG : 설정파일 추가, 수정
- TEST : 테스트 코트, 리팩토링 테스트 코드 추가
- CHORE : 빌드 업무 수정, 패키지 매니저 수정
- STYLE : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
- COMMENT :	필요한 주석 추가 및 변경
- DOCS : 문서를 수정한 경우
- RENAME : 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우
- REMOVE : 파일을 삭제하는 작업만 수행한 경우

## 깃 플로우
- main
  - dev
    - feat

> feat 네이밍 규칙 예시 : feat-oauth-kakao

> feat에서 개발 브랜치 생성후 feat에서 PR(pull request) -> 기능 테스트후 dev 브랜치로 PR
