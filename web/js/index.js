function deleteFruit(id) {
    if (confirm("Confirm delete?")) {
        window.location.href="/fruit.do?id=" + id + "&operate=delete";
        console.log(window.location.href)
    }
}

function toPage(pageNo) {
    if (pageNo <= 0) {
        pageNo = 1;
    }
    if (pageNo != 1) {
        window.location.href = ("/fruit.do?pageNo=" + pageNo);
    } else {
        window.location.href = "/fruit.do"
    }
}