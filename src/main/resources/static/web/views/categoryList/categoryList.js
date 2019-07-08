define(function (require, exports, module) {

    var categoryId = "";

    var Home = Backbone.View.extend({

        el: document.getElementsByTagName('body')[0],

        events: {
            "click .add-btn": "handlerAdd",
            "click .del-btn": "handlerDelete",
            "click .edit-btn": "handlerEdit"
        },

        template: _.template($('#treeTemplate').html()),
        bTemplate: _.template($('#buttonTemplate').html()),

        initialize: function () {
            this.model = new Backbone.Model();
            this.model.set("resourceData", resourceData);
            this.getTree();
            this.hideView();

        },

        render: function () {
            $("#tree-basic").empty().append(this.template(this.model.toJSON()));
            $("#toolBox").empty().append(this.bTemplate(this.model.toJSON()));
        },

        getTree: function () {

            utils.getJSON("/category/treeList", {}, function (res) {
                this.model.set("list", res);
                this.render();
                $("#tree-basic").treetable({expandable: true});
                $("#tree-basic").treetable("expandAll");
            }.bind(this))
        },

        handlerAdd: function () {
            window.location.href = "../addCategory/addCategory.html";
        },

        handlerDelete: function (event) {
            var target = $(event.currentTarget);
            categoryId = target.data("id");
            $(".alert-view .alert-txt", parent.document).text("确定要删除吗？");
            $(".alert-view", parent.document).show();

        },

        hideView: function () {
            var _this = this;

            $(".alert-view .s-btn", parent.document).click(function () {
                $(this).parent().parent().parent().hide();
                _this.handlerSureDel();
            })
        },

        handlerSureDel: function () {
            var _this = this;
            utils.getDelete("/category/" + categoryId, {}, function (res) {
                utils.showTip("删除成功");
                $(window).trigger("changeMenu");
                setTimeout(function () {
                    window.location.href = "../category/category.html";
                }, 1000);
            })
        },

        handlerEdit: function (event) {
            var target = $(event.currentTarget);
            var id = target.data("id");
            window.location.href = "../addCategory/addCategory.html?id=" + id;

        }


    });

    var home = new Home();

});

seajs.use('./categoryList.js');
