    function authorizationOpenBank() {
    fetch("/finpoint/bank/auth", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => response.text())
        .then(function (data) {
            window.open(data, "인증", "width=800px, height=800px");
        })
}

    function openProfileWindow() {
    window.open("/profile", '프로필 이미지 변경', 'width=400,height=300');
}
