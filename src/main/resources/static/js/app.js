function updateState() {

    $.get("/data/game", (r) => {
        console.log(r)
        $('#pre').val(r);
        $('#word').val(r.word)
    });
}

