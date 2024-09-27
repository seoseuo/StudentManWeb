document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('insertAdminForm');

    form.addEventListener('submit', (event) => {
        let isValid = true; 
        let focusSet = false; // 커서가 처음 실패한 입력 필드에만 이동하도록 함

        // 각 입력 폼 엘리먼트 가져오기
        var id_cursor = document.getElementById("id");
        var password_cursor = document.getElementById("password");
        var name_cursor = document.getElementById("name");
        var count_cursor = document.getElementById("count");

        // 아이디 유효성 검사
        const id = form.elements['id'].value;
        const idWarn = document.getElementById('id_warn');
        if (id === '') {
            idWarn.textContent = '* 아이디를 입력하세요.';
            isValid = false;
            if (!focusSet) {
                id_cursor.focus(); // 첫 번째 실패 항목에만 커서를 이동
                focusSet = true;
            }
        } else if (/\s/.test(id)) {
            idWarn.textContent = '* 아이디에 공백은 포함될 수 없습니다.';
            isValid = false;
            if (!focusSet) {
                id_cursor.focus();
                focusSet = true;
            }
        } else if (/[^a-zA-Z0-9]/.test(id)) {
            idWarn.textContent = '* 아이디는 영문자와 숫자만 입력 가능합니다.';
            isValid = false;
            if (!focusSet) {
                id_cursor.focus();
                focusSet = true;
            }
        } else {
            idWarn.textContent = ''; // 경고 메시지 초기화
        }

        // 비밀번호 유효성 검사
        const password = form.elements['password'].value;
        const passwordWarn = document.getElementById('pw_warn');
        if (password === '') {
            passwordWarn.textContent = '* 비밀번호를 입력하세요.';
            isValid = false;
            if (!focusSet) {
                password_cursor.focus();
                focusSet = true;
            }
        } else if (/\s/.test(password)) {
            passwordWarn.textContent = '* 비밀번호에 공백은 포함될 수 없습니다.';
            isValid = false;
            if (!focusSet) {
                password_cursor.focus();
                focusSet = true;
            }
        } else {
            passwordWarn.textContent = ''; 
        }

        // 이름 유효성 검사
        const name = form.elements['name'].value;
        const nameWarn = document.getElementById('name_warn'); 
        if (name === '') {
            nameWarn.textContent = '* 이름을 입력하세요.';
            isValid = false;
            if (!focusSet) {
                name_cursor.focus();
                focusSet = true;
            }
        } else if (/\s/.test(name)) {
            nameWarn.textContent = '* 이름에 공백은 포함될 수 없습니다.';
            isValid = false;
            if (!focusSet) {
                name_cursor.focus();
                focusSet = true;
            }
        } else if (!/^[가-힣]+$/.test(name)) {
            nameWarn.textContent = '* 이름은 올바른 한글만 입력 가능합니다.';
            isValid = false;
            if (!focusSet) {
                name_cursor.focus();
                focusSet = true;
            }
        } else {
            nameWarn.textContent = ''; 
        }

        // 등록할 학생 수 유효성 검사
        const count = form.elements['count'].value;
        const countWarn = document.getElementById('count_warn');
        if (count === '') {
            countWarn.textContent = '* 등록할 학생 수를 입력하세요.';
            isValid = false;
            if (!focusSet) {
                count_cursor.focus();
                focusSet = true;
            }
        } else if (!/^\d+$/.test(count) || parseInt(count, 10) <= 0) {
            countWarn.textContent = '* 등록 학생 수는 양의 정수여야 합니다.';
            isValid = false;
            if (!focusSet) {
                count_cursor.focus();
                focusSet = true;
            }
        } else if (/\s/.test(count)) {
            countWarn.textContent = '* 등록 학생 수에 공백은 포함될 수 없습니다.';
            isValid = false;
            if (!focusSet) {
                count_cursor.focus();
                focusSet = true;
            }
        } else {
            countWarn.textContent = ''; 
        }

        // 유효성 검사 실패 시 폼 제출 방지
        if (!isValid) {
            event.preventDefault(); 
        }
    });
});
