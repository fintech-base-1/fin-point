$(document).ready(function () {
    $('#login').submit(function (event) {
        event.preventDefault();

        let email = $('#email').val();
        if (email === '') {
            sweetalert('error', '입력 오류', '이메일을 입력하세요');
            return false;
        } else {
            let password = $('#password').val();
            if (password === '') {
                sweetalert('error', '입력 오류', '비밀번호를 입력하세요');
                return false;
            }
        }


        const loginMemberDto = {
            email: document.getElementById('email').value,
            password: document.getElementById('password').value
        }
        fetch('/finpoint/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginMemberDto)
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = "/finpoint/mail-confirm";
                } else {
                    sweetalert('error', '입력 오류', '존재하지 않는 회원입니다.');
                }
            })
            .catch(error => {
                console.log("전송 오류");
            });
    });
});

let sweetalert = (icon, title, contents) => {
    Swal.fire({
        icon: icon,
        title: title,
        text: contents,
        confirmButtonText: "확인"
    });
};
document.getElementById('googleLogin').addEventListener('click', function () {
    fetch("/finpoint/oauth/google", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => response.text())
        .then(data => {
            const login = window.open(data, "로그인", 'width=600,height=800, top=100, left=700, scrollbars=yes, resizable=yes')
            const check = setInterval(()=> {
                if (login.window.location.href.includes('finpoint')) {
                    login.close()
                    window.location.href = '/';
                }
                if (!login || login.closed) {
                    clearInterval(check);
                }
            }, 1000);
        })
})

document.getElementById('naverLogin').addEventListener('click', function () {
    fetch("/finpoint/oauth/naver", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => response.text())
        .then(function (data) {
            const login = window.open(data, "로그인", "width=800px,height=800px")
            const check = setInterval(()=> {
                if (login.window.location.href.includes('finpoint')) {
                    login.close()
                    window.location.href = '/';
                }
                if (!login || login.closed) {
                    clearInterval(check);
                }
            }, 1000);
        })
})

document.getElementById('kakaoLogin').addEventListener('click', function () {
    fetch("/finpoint/oauth/kakao", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => response.text())
        .then(function (data) {
            const login = window.open(data, "로그인", "width=500px,height=800px")
            const check = setInterval(()=> {
                if (login.window.location.href.includes('finpoint')) {
                    login.close()
                    window.location.href = '/';
                }
                if (!login || login.closed) {
                    clearInterval(check);
                }
            }, 1000);
        })
})