define(function (require, exports, module) {

    var commodityTable;

    var code;

    var commodityId;

    var Home = Backbone.View.extend({

        el: document.getElementsByTagName('body')[0],

        events: {
            "click .edit-btn": "handlerEdit",
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

        initData: function () {
            commodityTable = $('#table').DataTable({
                "ajax": {
                    url: baseUrl + "/commodity/tables"
                },
                "columns": [
                    {"data": "name"},
                    {"data": "description"},
                    {"data": "price"},
                    {"data": "shopName"},
                    {"data": "categoryName"},
                    {
                        render: function (data, type, row, meta) {
                            var str = "";

                            if ($.inArray("/commodity-put", resourceData) > -1) {
                                str += "<button  data-text='编辑商品'  data-id='addCommodity' data-link='../addCommodity/addCommodity.html?id=" + row.id + "' class='btn btn-primary edit-btn btn-xs margin-right-5'><i class='fa fa-pencil' aria-hidden='true'></i> 编辑</button>"
                            }
                            
                            if ($.inArray("/commodity/*-delete", resourceData) > -1) {
                                str += "<button data-id='" + row.id + "' class='btn btn-danger del-btn btn-xs'><i class='fa fa-trash-o' aria-hidden='true'></i> 删除</button>"
                            }
                            return str;
                        }


                    }
                ]
            });
        },

        handlerIdSearch: function () {
           commodityId = $(".commodity-id").val();

           if(commodityId == "" ){
                utils.showTip("请填入商品Id信息");
                return;
            }

           commodityId=parseInt($(".commodity-id").val());

            window.location.href='../addCommodity/addCommodity.html?id='+ commodityId;
        },

        handlerNameSearch: function(){
            var commodityName = $(".commodity-name").val();

            if(commodityName == "" ){
                utils.showTip("请填入商品名字");
                return;
            }


        },

        handlerEdit: function (event) {
            addTab(event, true);
        },
        
        handlerDelete: function (event) {
            var _this=this;
            var target = $(event.currentTarget);
            commodityId = target.data("id");

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
            utils.getDelete("/commodity/" + commodityId, {}, function (res) {
                utils.showTip("删除成功");
                setTimeout(function () {
                    commodityTable.ajax.reload(null, false);
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
            commodityId = target.data("id");
            utils.getJSON("/commodity/" + commodityId, {}, function (res) {
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
            commodityTable.ajax.reload(null, false);
        }


    });

    var home = new Home();

});

seajs.use('./commodityList.js');
