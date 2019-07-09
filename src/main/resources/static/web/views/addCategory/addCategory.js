define(function (require, exports, module) {

    var list = [];

    var zTree;

    var pid;

    var id = utils.getQuery("id");

    var Home = Backbone.View.extend({

        el: document.getElementsByTagName('body')[0],

        events: {
            "click .sure-btn": "handlerSure",
            "click .inp-tree": "handlerShow"
        },

        initialize: function () {
            this.model = new Backbone.Model();
            this.getMenu();

        },

        getMenu: function () {
            utils.getPOST("/category/menus", {}, function (res) {
                list = res;
                this.model.set("onelist", res);
                this.initData();
                this.initTree(res);

            }.bind(this));

        },

        handlerSure: function () {
            var name = $(".name").val();
            var postData = {
                "name": name,
            }
            if (name == "") {
                utils.showTip("请输入商品分类名");
                return;
            }

            if (typeof pid != "undefined") {
                postData["pid"] = pid;
            }


            if (id) {
                this.handlerEdit(postData);
            } else {
                this.handlerAdd(postData);
            }
        },

        handlerAdd: function (postData) {
            utils.getPOST("/category", postData, function (res) {
                utils.showTip("添加成功");
                $(window, parent.document).trigger("changeMenu");
                setTimeout(function () {
                    window.location.href = "../categoryList/categoryList.html";
                }, 1000);

            })
        },

        handlerEdit: function (postData) {
            postData["id"] = id;
            utils.getPUT("/category", postData, function (res) {
                utils.showTip("修改成功");
                $(window, parent.document).trigger("changeMenu");
                setTimeout(function () {
                    window.location.href = "../categoryList/categoryList.html";
                }, 1000);

            })
        },

        initData: function () {
            var _this = this;
            if (id) {
                $(".sure-btn").text("修改");
                utils.getJSON("/category/" + id, {}, function (res) {
                    _this.dealData(res);
                })
            }
        },

        dealData: function (res) {
            $(".name").val(res.name);
            $(".seq").val(res.seq);

            if (typeof res.pid != "undefined") {
                pid = res.pid;
                $(".inp-tree").val(res.pname);
            }
            if (res.remark) {
                $(".remark").val(res.remark);
            }
        },

        initTree: function (res) {
            var setting = {
                view: {
                    dblClickExpand: false
                },
                data: {
                    key: {
                        name: "text",
                        children: "children",
                        url: "url1"
                    }
                },
                callback: {
                    onClick: this.onClick
                }
            };
            zTree = $.fn.zTree.init($("#tree"), setting, res);
        },

        onClick: function () {
            var nodes = zTree.getSelectedNodes();
            var text = nodes[0].text;
            pid = nodes[0].id;
            $(".inp-tree").val(text);
            $("#tree").hide();
        },

        handlerShow: function () {
            $("#tree").toggle();
        }


    });

    var home = new Home();

});

seajs.use('./addCategory.js');
