let currentPage = 1;
let standard = 'type';

const buttons = document.querySelectorAll('.standard');
buttons.forEach(botton => {
    botton.addEventListener('click', function() {
        standard = this.id;
        loadMemberPage();
    });
});

function loadMemberPage() {
    fetch(`/finpoint/ranking/data`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            standard: standard,
            page: currentPage
        })
    })
        .then(response => response.json())
        .then(members => {
            displayMembers(members);
        })
        .catch(error => {
            console.error('T_T : ', error);
        });
}

function createMemberElement(member) {
    const li = document.createElement('li');

    const id = member.userId;

    const img = document.createElement('img');
    img.onerror = function() {
        img.src = "/images/default.jpg";
    };
    img.src = "/finpoint/ranking/image/"+id;
    li.appendChild(img);

    // const nicknameDiv = document.createElement('div');
    // nicknameDiv.textContent = member.nickname;
    // li.appendChild(nicknameDiv);

    const emailDiv = document.createElement('div');
    emailDiv.textContent = member.email;
    li.appendChild(emailDiv);

    const typeCountDiv = document.createElement('div');
    typeCountDiv.textContent = member.typeCount;
    li.appendChild(typeCountDiv);

    const pieceRetainCountDiv = document.createElement('div');
    pieceRetainCountDiv.textContent = member.pieceRetainCount;
    li.appendChild(pieceRetainCountDiv);

    const assetAmountDiv = document.createElement('div');
    assetAmountDiv.textContent = member.assetAmount;
    li.appendChild(assetAmountDiv);

    return li;
}

function displayMembers(members) {
    const memberList = document.getElementById('memberList');

    members.forEach(member => {
        const memberElement = createMemberElement(member);
        memberList.appendChild(memberElement);
    });
}

window.addEventListener('scroll', function () {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        currentPage++;
        loadMemberPage();
    }
});
window.onload = loadMemberPage;
