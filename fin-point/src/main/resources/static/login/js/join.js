document.getElementById('join')
    .addEventListener('submit', function (e) {
    e.preventDefault();

    const memberDto = {
        email: document.getElementById('email').value,
        password: document.getElementById('password').value
    }

    fetch('/finpoint/join', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(memberDto)
    })
        .then(response => {
            if (response.ok) {
                window.location.href = "/finpoint/login";
            } else {
                console.log("응답 오류")
                alert(`Response Status: ${response.status}`);
            }
        })
        .catch(error => {
            console.log("전송 오류")
        })
})