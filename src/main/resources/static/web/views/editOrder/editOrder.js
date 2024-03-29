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
            var state = $(".type-sel").val();
            var postData = {
                "state": state,
            }
            this.handlerEdit(postData);
        },

        handlerEdit: function (postData) {
            postData["id"] = id;
            var _this = this;
            utils.getPUT("/order/editState", postData, function (res) {
                utils.showTip("修改成功");

                setTimeout(function () {
                    _this.refreshData();
                }, 1000);

            })
        },

        initData: function () {
            var _this = this;
            if (id) {
                utils.getJSON("/order/" + id, {}, function (res) {
                    _this.dealData(res);
                })
            }
        },

        dealData: function (res) {
            $(".state").val(res.state);

        },

        refreshData: function () {
            closeTab(frameElement);
        }


    });

    var home = new Home();

});

seajs.use('./editOrder.js');
