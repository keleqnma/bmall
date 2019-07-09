define(function (require, exports, module) {


    var id = utils.getQuery("id");

    var Home = Backbone.View.extend({

        el: document.getElementsByTagName('body')[0],

        events: {
            "click .sure-btn": "handlerSure"
        },

        initialize: function () {
            this.model = new Backbone.Model();
            this.initData();

        },

        handlerSure: function () {
            var buyerName = $(".buyer-name").val();

            var postData = {
                "name": buyerName,
            }
            if (buyerName == "") {
                utils.showTip("请输入买家名");
                return;
            }
            if (id) {
                this.handlerEdit(postData);
            }
            else {
                this.handlerAdd(postData);
            }
        },

        handlerAdd: function (postData) {
            var _this = this;
            utils.getPOST("/buyer", postData, function (res) {
                utils.showTip("添加成功");
                setTimeout(function () {
                    _this.refreshData();
                }, 1000);

            })
        },

        handlerEdit: function (postData) {
            var _this = this;
            postData["id"] = id;
            utils.getPUT("/buyer", postData, function (res) {
                utils.showTip("修改成功");

                setTimeout(function () {
                    _this.refreshData();
                }, 1000);

            })
        },

        initData: function () {
            var _this = this;
            if (id) {
                $(".sure-btn").text("修改");
                $(".p-line").hide();
                utils.getJSON("/buyer/" + id, {}, function (res) {
                    _this.dealData(res);
                })
            }
        },

        dealData: function (res) {
            $(".buyer-name").val(res.name);
        },

        refreshData: function () {
            closeTab(frameElement);
        }


    });

    var home = new Home();

});

seajs.use('./addBuyer.js');