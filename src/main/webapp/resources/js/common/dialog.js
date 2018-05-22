var Dialog = function () {
};
Dialog.prototype.showLongDialog = function () {
    $('.login_pop').remove();
    $('.zhezhao').remove();
    var dialogHtml = '<div class="zhezhao"></div>';
    dialogHtml+='<div class="login login_long"></div>';
    $('body').append(dialogHtml);
    $('.login_pop').show();
    $('.zhezhao').show();
};
Dialog.prototype.showShortDialog = function () {
    $('.login_pop').remove();
    $('.zhezhao').remove();
    var dialogHtml = '<div class="zhezhao"></div>';
    dialogHtml+='<div class="login"></div>';
    $('body').append(dialogHtml);
    $('.login_pop').show();
    $('.zhezhao').show();
};
Dialog.prototype.showAreaDialog = function () {
    $('.login_pop').remove();
    $('.zhezhao').remove();
    var dialogHtml = '<div class="zhezhao"></div>';
    dialogHtml+='<div class="login login_textarea"></div>';
    $('body').append(dialogHtml);
    $('.login_pop').show();
    $('.zhezhao').show();
};
Dialog.prototype.closeDialog=function () {
    $('.login').remove();
    $('.zhezhao').remove();
};



























