document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('loginAdminForm');
    // 폼을 제출하기 전에 데이터 입력 유효성 검사를 하기 위함.

    form.addEventListener('submit', (event) => {

        // 유효성 검사 결과를 저장할 변수
        let isValid = true; // 기존 값 참, 예외 발생 시 거짓으로 처리하여 제출을 방지함.

        // 아이디 유효성 검사
        const id = form.elements['id'].value; // 입력한 Id 값 가져옴.

        const idWarn = document.getElementById('id_warn');
        if (id === '') {
            idWarn.textContent = '* 아이디를 입력하세요.'; // 1. 아무 값도 입력하지 않았을 때
            isValid = false;
        } else if (/\s/.test(id)) {
            idWarn.textContent = '* 아이디에 공백은 포함될 수 없습니다.'; // 2. 공백이 포함되어 있을 때
            isValid = false;
        } else if (/[\u3131-\uD79D]/.test(id)) { // 3. 한글이 포함되어 있을 때
            idWarn.textContent = '* 아이디에 한글은 포함될 수 없습니다.';
            isValid = false;
        } else { // 오류 없을 때
            idWarn.textContent = ''; // 경고 메시지 초기화
        }

        // 비밀번호 유효성 검사
        const password = form.elements['password'].value; // 입력한 password 값 가져옴.
        const passwordWarn = document.getElementById('pw_warn');
        if (password === '') {
            passwordWarn.textContent = '* 비밀번호를 입력하세요.'; // 1. 아무 값도 입력하지 않았을 때
            isValid = false;
        } else if (/\s/.test(password)) {
            passwordWarn.textContent = '* 비밀번호에 공백은 포함될 수 없습니다.'; // 2. 공백이 포함되어 있을 때
            isValid = false;
        } else {
            passwordWarn.textContent = ''; // 경고 메시지 초기화
        }


        // 유효성 검사 실패 시 폼 제출 방지
        if (!isValid) {
            event.preventDefault(); // 폼 제출 방지
        }
    });
});
