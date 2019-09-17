define(function (require, exports, module) {

    var userTable;

    var code;

    var userId;

    var Home = Backbone.View.extend({

        el: document.getElementsByTagName('body')[0],

        events: {
            "click .edit-btn": "handlerEdit",
            "click .pwd-btn": "handlerPwd",
            "click .del-btn": "handlerDelete",
            "click .add-btn": "handlerAdd",
            "click .close-btn": "handlerClose",
            "click .refresh-btn": "handlerRefresh",
            "click .sure-btn2": "handlerIdSearch",
            "click .sure-btn1": "handlerNameSearch",
        },
        template: _.template($('#buttonTemplate').html()),

        initialize: function () {
            this.model = new Backbone.Model();
            this.model.set("resourceData", resourceData);
            this.render();
            this.initData();
            this.hideView();
            this.getData();

        },

        render: function () {
            $("#toolBox").empty().append(this.template(this.model.toJSON()));
        },

        dealData: function(url){
            userTable = $('#table').DataTable({
                "ajax": {
                    url: baseUrl + url
                },
                "columns": [
                    {"data": "username"},
                    {"data": "name"},
                    {"data": "createTime"},
                    {
                        render: function (data, type, row, meta) {
                            var str = "";

                            if ($.inArray("/user-put", resourceData) > -1) {
                                str += "<button  data-text='编辑用户'  data-id='addUser' data-link='../addUser/addUser.html?id=" + row.id + "' class='btn btn-primary edit-btn btn-xs margin-right-5'><i class='fa fa-pencil' aria-hidden='true'></i> 编辑</button>"
                            }

                            if ($.inArray("/user/editPwd-post", resourceData) > -1) {
                                str += "<button data-text='修改密码'  data-id='editPwd' data-link='../editPassword/editPassword.html?id=" + row.id + "' class='btn btn-primary pwd-btn btn-xs margin-right-5'><i class='fa fa-key' aria-hidden='true'></i> 修改密码</button>"
                            }

                            if ($.inArray("/user/*-delete", resourceData) > -1) {
                                str += "<button data-id='" + row.id + "' class='btn btn-danger del-btn btn-xs'><i class='fa fa-trash-o' aria-hidden='true'></i> 删除</button>"
                            }
                            return str;
                        }


                    }
                ]
            });
        },

        initData: function () {
            var _this = this;

            _this.dealData("/user/shopTables");
        },

        handlerIdSearch: function () {
            shopId = $(".shop-id").val();

            if(shopId == "" ){
                utils.showTip("请填入商店Id信息");
                return;
            }

            shopId=parseInt($(".shop-id").val());

            window.location.href='../addUser/addUser.html?id='+ shopId;
        },

        handlerNameSearch: function(){
            var commodityName = $(".shop-name").val();

            if(commodityName == "" ){
                utils.showTip("请填入商店名字");
                return;
            }


        },
        
        handlerEdit: function (event) {
            addTab(event, true);
        },

        handlerPwd: function (event) {
            addTab(event, true);
        },

        handlerDelete: function (event) {
            var target = $(event.currentTarget);
            resourceId = target.data("id");
            $(".alert-view .alert-txt", parent.document).text("确定要删除吗？");
            $(".alert-view", parent.document).show();

        },

        hideView: function () {
            var _this = this;

            $(".alert-view .s-btn", parent.document).click(function () {
                $(".alert-view", parent.document).hide();
                _this.handlerSureDel();
            })
        },

        handlerSureDel: function () {
            var _this = this;
            utils.getDelete("/user/" + resourceId, {}, function (res) {
                utils.showTip("删除成功");
                setTimeout(function () {
                    userTable.ajax.reload(null, false);
                }, 1000);
            })
        },

        handlerAdd: function (event) {
            var id = $(".item-ul li.active", parent.document).data("id");
            addTab(event, true, id);
        },

        getData: function () {
            utils.getJSON("/role/tree", {}, function (res) {
                this.initTree(res);

            }.bind(this));

        },

        initTree: function (res) {
            var setting = {
                view: {
                    dblClickExpand: false
                },
                check: {
                    enable: true
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "pid"
                    }
                }
            };
            zTree = $.fn.zTree.init($("#role"), setting, res);
            this.setCheck();
            $("#py").bind("change", this.setCheck);
            $("#sy").bind("change", this.setCheck);
            $("#pn").bind("change", this.setCheck);
            $("#sn").bind("change", this.setCheck);
        },

        setCheck: function () {
            var zTree = $.fn.zTree.getZTreeObj("role"),
                py = $("#py").attr("checked") ? "p" : "",
                sy = $("#sy").attr("checked") ? "s" : "",
                pn = $("#pn").attr("checked") ? "p" : "",
                sn = $("#sn").attr("checked") ? "s" : "",
                type = {"Y": py + sy, "N": pn + sn};
            zTree.setting.check.chkboxType = type;
            this.showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
        },
        showCode: function (str) {
            if (!code) code = $("#code");
            code.empty();
            code.append("<li>" + str + "</li>");
        },

        handlerClose: function (event) {
            var target = $(event.currentTarget);
            target.parent().parent().hide();
        },

        handlerShow: function (event) {
            var target = $(event.currentTarget);
            var _this = this;
            userId = target.data("id");
            utils.getJSON("/user/" + userId, {}, function (res) {
                _this.initEdit(res);
            })


        },

        initEdit: function (res) {
            var treeObj = $.fn.zTree.getZTreeObj("role");
            var data = res.roleIds;
            var nodes = treeObj.transformToArray(treeObj.getNodes());
            treeObj.checkAllNodes(false);

            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < nodes.length; j++) {
                    if (data[i] == nodes[j].id) {
                        treeObj.checkNode(nodes[j], true, true);
                    }
                }
            }
            $(".role-view").show();
        },

        handlerRefresh: function () {
            userTable.ajax.reload(null, false);
        }


    });

    var home = new Home();

});

seajs.use('./shopList.js');
