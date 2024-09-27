document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('serchStudentForm');
    let focusSet = false; // 커서 이동을 한 번만 실행하도록 설정

    // 폼을 제출하기 전에 데이터 입력 유효성 검사를 하기 위함.
    form.addEventListener('submit', (event) => {
        let isValid = true; // 기존 값 참, 예외 발생 시 거짓으로 처리하여 제출을 방지함.

        // 학번 유효성 검사
        const num = form.elements['num'].value; // 입력한 num 값 가져옴.
        const numWarn = document.getElementById('num_warn');
        const numCursor = document.getElementById('num'); // 커서 이동을 위한 요소

        if (num === '') {
            numWarn.textContent = '* 학번을 입력하세요.';
            isValid = false;
            if (!focusSet) {
                numCursor.focus();
                focusSet = true;
            }
        } else if (/\s/.test(num)) {
            numWarn.textContent = '* 학번에 공백은 포함될 수 없습니다.';
            isValid = false;
            if (!focusSet) {
                numCursor.focus();
                focusSet = true;
            }
        } else if (!/^20\d{6}$/.test(num)) { 
            numWarn.textContent = '* 학번 양식은 20****** 입니다.';
            isValid = false;
            if (!focusSet) {
                numCursor.focus();
                focusSet = true;
            }
        } else { 
            numWarn.textContent = ''; // 경고 메시지 초기화
        }

        // 유효성 검사 실패 시 폼 제출 방지
        if (!isValid) {
            event.preventDefault(); // 폼 제출 방지
        }
    });
});
