let currentPage = 0;

async function loadMemberPage() {
    try {
        const response = await fetch(`/ranking/data?page=${currentPage}`);
        const memberPage = await response.json();
        displayMembers(memberPage.content);
    } catch (error) {
        console.error('Error:', error);
    }
}

function createMemberElement(member) {
    const li = document.createElement('li');

    const img = document.createElement('img');
    img.src = "/images/" + member.image;
    li.appendChild(img);

    const nicknameDiv = document.createElement('div');
    nicknameDiv.textContent = member.nickname;
    li.appendChild(nicknameDiv);

    const emailDiv = document.createElement('div');
    emailDiv.textContent = member.email;
    li.appendChild(emailDiv);

    const priceDiv = document.createElement('div');
    priceDiv.textContent = member.totalPrice;
    li.appendChild(priceDiv);

    return li;
}

function displayMembers(members) {
    const memberList = document.getElementById('memberList');

    members.forEach(member => {
        const memberElement = createMemberElement(member);
        memberList.appendChild(memberElement);
    });
}

window.addEventListener('scroll', function() {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        currentPage++;
        loadMemberPage();
    }
});

window.onload = loadMemberPage;
