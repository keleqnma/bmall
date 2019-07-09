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
            var commodityName = $(".commodity-name").val();
            var price = $(".price").val();
            var description = $(".description").val();

            var postData = {
                "name": commodityName,
                "price":price,
                "description":description,
            }

            if (commodityName == "") {
                utils.showTip("请输入商品名");
                return;
            }
            if (price == "") {
                utils.showTip("请输入商品单价");
                return;
            }
            if (description == "") {
                utils.showTip("请输入商品描述");
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
            utils.getPOST("/commodity", postData, function (res) {
                utils.showTip("添加成功");
                setTimeout(function () {
                    _this.refreshData();
                }, 1000);

            })
        },

        handlerEdit: function (postData) {
            var _this = this;
            postData["id"] = id;
            utils.getPUT("/commodity", postData, function (res) {
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
                utils.getJSON("/commodity/" + id, {}, function (res) {
                    _this.dealData(res);
                })
            }
        },

        dealData: function (res) {
            $(".commodity-name").val(res.name);
            $(".price").val(res.price);
            $(".description").val(res.description);
        },

        refreshData: function () {
            closeTab(frameElement);
        }


    });

    var home = new Home();

});

seajs.use('./addCommodity.js');
