/**
 * Created by 李明元 on 2017/3/28.
 */
$(function () {
    $(document).on("pageInit", function(e, pageId, $page) {
        if(pageId == "newsPage") {

        }

    });
    $.init();
});

function newsShow(id) {
    $.router.load("app/news/show/"+id,true);
}
