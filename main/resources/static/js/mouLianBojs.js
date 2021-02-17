window.onLoad=function(){
    var myChart = echarts.init(document.getElementById('main'));
    var mydata = [[${nums2}]];   //  thymeleaf 表达式在JS中的使用，注意：这里是数字类型
    var aa = "[[${cate2}]]";
//***
    var title = [];//一个算法，字符能显示，字符类型必须要搞
    var info = '';
    var index = 0;
    var onex = "";
    aa = aa.substr(1, aa.length);
    var len = mydata.length;
    for (var i = 1; i <= len; i++) {
        info += "第" + i + "次：&nbsp;&nbsp;&nbsp;";
        index = aa.indexOf(',');
        if (len == i) {
            onex = aa.substring(0, aa.length - 1);
        } else {
            onex = aa.substring(0, index);
        }
        info += "aa:  " + aa;
        info += "&nbsp;&nbsp;&nbsp; onex: " + onex + "<br/>";
        aa = aa.substr(index + 1, aa.length);
        title.push(onex);
    }
//****
    option = {
        title: {
            text: '热门简历模板排行榜'
        },
        tooltip: {//提示效果
            trigger: 'axis',
            axisPointer: {
                type: 'cross'//鼠标碰到的时候cross是十字准星，show是阴影
            }
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },//下载这个图
        grid: {
            top: '20%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },//和边的距离

        xAxis: {
            type: 'category',
            axisLabel: {
                interval: 0,
                rotate: 45,
            },
            data: title,//动态的title就给data了
        },

        yAxis: {
            type: 'value'
        },
        series: [{
            data: mydata,//动态的给data了
            barWidth: 50,
            type: 'line'

        }]
    };//option之后setoption就是表格，bar是柱状图，line是折线图
//使用指定的配置项和数据显示图标
    myChart.setOption(option);

//模板下载排行榜
    var myChart2 = echarts.init(document.getElementById('resume'));
    var mydata = [[${nums2}]];   //  thymeleaf 表达式在JS中的使用，注意：这里是数字类型
    var aa = "[[${cate2}]]";
//***
    var title = [];//一个算法，字符能显示，字符类型必须要搞
    var info = '';
    var index = 0;
    var onex = "";
    aa = aa.substr(1, aa.length);
    var len = mydata.length;
    for (var i = 1; i <= len; i++) {
        info += "第" + i + "次：&nbsp;&nbsp;&nbsp;";
        index = aa.indexOf(',');
        if (len == i) {
            onex = aa.substring(0, aa.length - 1);
        } else {
            onex = aa.substring(0, index);
        }
        info += "aa:  " + aa;
        info += "&nbsp;&nbsp;&nbsp; onex: " + onex + "<br/>";
        aa = aa.substr(index + 1, aa.length);
        title.push(onex);
    }
//****
    option2 = {
        title: {
            text: '热门简历模板排行榜'
        },
        tooltip: {//提示效果
            trigger: 'axis',
            axisPointer: {
                type: 'cross'//鼠标碰到的时候cross是十字准星，show是阴影
            }
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },//下载这个图
        grid: {
            top: '20%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },//和边的距离

        xAxis: {
            type: 'category',
            axisLabel: {
                interval: 0,
                rotate: 45,
            },
            data: title,//动态的title就给data了
        },

        yAxis: {
            type: 'value'
        },
        series: [{
            data: mydata,//动态的给data了
            barWidth: 50,
            type: 'line'

        }]
    };//option之后setoption就是表格，bar是柱状图，line是折线图
//使用指定的配置项和数据显示图标
    myChart2.setOption(option2);

//职业需求排行榜
    var myChart3 = echarts.init(document.getElementById('vocation'));
    var mydata = [[${nums2}]];   //  thymeleaf 表达式在JS中的使用，注意：这里是数字类型
    var aa = "[[${cate2}]]";
//***
    var title = [];//一个算法，字符能显示，字符类型必须要搞
    var info = '';
    var index = 0;
    var onex = "";
    aa = aa.substr(1, aa.length);
    var len = mydata.length;
    for (var i = 1; i <= len; i++) {
        info += "第" + i + "次：&nbsp;&nbsp;&nbsp;";
        index = aa.indexOf(',');
        if (len == i) {
            onex = aa.substring(0, aa.length - 1);
        } else {
            onex = aa.substring(0, index);
        }
        info += "aa:  " + aa;
        info += "&nbsp;&nbsp;&nbsp; onex: " + onex + "<br/>";
        aa = aa.substr(index + 1, aa.length);
        title.push(onex);
    }
//****
    option3 = {
        title: {
            text: '模板下载排行榜'
        },
        tooltip: {//提示效果
            trigger: 'axis',
            axisPointer: {
                type: 'cross'//鼠标碰到的时候cross是十字准星，show是阴影
            }
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },//下载这个图
        grid: {
            top: '20%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },//和边的距离

        xAxis: {
            type: 'category',
            axisLabel: {
                interval: 0,
                rotate: 45,
            },
            data: title,//动态的title就给data了
        },

        yAxis: {
            type: 'value'
        },
        series: [{
            data: mydata,//动态的给data了
            barWidth: 50,
            type: 'line'

        }]
    };//option之后setoption就是表格，bar是柱状图，line是折线图
//使用指定的配置项和数据显示图标
    myChart3.setOption(option3);

//岗位需求排行榜
    var myChart4 = echarts.init(document.getElementById('users'));
    option4 = {
        title: {
            text: '求职加油站系统用户比例',
            subtext: '',
            left: 'true'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20,
            data: ['普通户用', 'VIP用户', 'HR', '高级管理员', '普通管理员']
        },
        series: [
            {
                name: '姓名',
                type: 'pie',
                radius: '55%',
                center: ['40%', '50%'],
                data: [
                    {value: 1548, name: '普通户用', selected: true},
                    {value: 1548, name: 'VIP用户', selected: true},
                    {value: 679, name: 'HR', selected: true},
                    {value: 5, name: '高级管理员', selected: false},
                    {value: 8, name: '普通管理员', selected: false}
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    myChart4.setOption(option4);

    var myChart5 = echarts.init(document.getElementById('users2'));
    option5 = {
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 10,
            data: ['直达', '营销广告', '搜索引擎', '邮件营销', '联盟广告', '视频广告', '百度', '谷歌', '必应', '其他']
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                selectedMode: 'single',
                radius: [0, '30%'],

                label: {
                    position: 'inner'
                },
                labelLine: {
                    show: false
                },
                data: [
                    {value: 335, name: '直达', selected: true},
                    {value: 679, name: '营销广告'},
                    {value: 1548, name: '搜索引擎'}
                ]
            },
            {
                name: '访问来源',
                type: 'pie',
                radius: ['40%', '55%'],
                label: {
                    formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                    backgroundColor: '#eee',
                    borderColor: '#aaa',
                    borderWidth: 1,
                    borderRadius: 4,
                    // shadowBlur:3,
                    // shadowOffsetX: 2,
                    // shadowOffsetY: 2,
                    // shadowColor: '#999',
                    // padding: [0, 7],
                    rich: {
                        a: {
                            color: '#999',
                            lineHeight: 22,
                            align: 'center'
                        },
                        // abg: {
                        //     backgroundColor: '#333',
                        //     width: '100%',
                        //     align: 'right',
                        //     height: 22,
                        //     borderRadius: [4, 4, 0, 0]
                        // },
                        hr: {
                            borderColor: '#aaa',
                            width: '100%',
                            borderWidth: 0.5,
                            height: 0
                        },
                        b: {
                            fontSize: 16,
                            lineHeight: 33
                        },
                        per: {
                            color: '#eee',
                            backgroundColor: '#334455',
                            padding: [2, 4],
                            borderRadius: 2
                        }
                    }
                },
                data: [
                    {value: 335, name: '直达'},
                    {value: 310, name: '邮件营销'},
                    {value: 234, name: '联盟广告'},
                    {value: 135, name: '视频广告'},
                    {value: 1048, name: '百度'},
                    {value: 251, name: '谷歌'},
                    {value: 147, name: '必应'},
                    {value: 102, name: '其他'}
                ]
            }
        ]
    };
    myChart5.setOption(option5);
}