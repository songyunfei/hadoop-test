/**
 * This jQuery plugin displays pagination links inside the selected elements.
 *
 * @author Gabriel Birke (birke *at* d-scribe *dot* de)
 * @version 1.2
 * @param {int} maxentries Number of entries to paginate
 * @param {Object} opts Several options (see README for documentation)
 * @return {Object} jQuery Object
 */
jQuery.fn.pagination = function(maxentries, opts){
    opts = jQuery.extend({
        items_per_page:10,       //每页显示条数
        num_display_entries:8,  //主体页数
        current_page:0,         //当前页数
        num_edge_entries:1,    //边缘页数
        link_to:"#",           //链接跳转地址
        prev_text:"",      //上一页 说明  为空时不显示按钮
        next_text:"",     //下一页说明  为空时不显示按钮
        first_text:"",     //首页说明    为空时不显示按钮
        end_text:"",       //末页说明  为空时不显示按钮
        skip_text:"",    //跳转按钮说明  //为空时显示跳转按钮
        skip_container:"<li class='jump' id='jump'></li>", //跳转按钮容器
        ellipse_text:"<i class='ion-more'>",   //省略
        prev_show_always:true,   //是否显示上一页
        next_show_always:true,   //是否显示下一页
        ellipsis_class:"more",  //省略样式
        selected_class:"active", //选中样式
        prev_class:"", //上一页样式
        next_class:"", //下一页样式
        first_class:"", //首页样式
        end_class:"", //末页样式
        ul_class:"pagination",  //ul 样式
        ski_input_class:"",  //跳转文本输入框样式
        ski_but_class:"btn",  //跳转按钮样式
        ski_but_theme:"",  //
        callback:function(){return false;}
    },opts||{});

    return this.each(function(thisIndex,thisItem) {
        /**
         * 计算最大分页显示数目
         */
        function numPages() {
            return Math.ceil(maxentries/opts.items_per_page);
        }
        /**
         * 极端分页的起始和结束点，这取决于current_page 和 num_display_entries.
         * @返回 {数组(Array)}
         */
        function getInterval()  {
            var ne_half = Math.ceil(opts.num_display_entries/2);
            var np = numPages();
            var upper_limit = np-opts.num_display_entries;
            var start = current_page>ne_half?Math.max(Math.min(current_page-ne_half, upper_limit), 0):0;
            var end = current_page>ne_half?Math.min(current_page+ne_half, np):Math.min(opts.num_display_entries, np);
            return [start,end];
        }

        /**
         * 分页链接事件处理函数
         * @参数 {int} page_id 为新页码
         */
        function pageSelected(page_id, evt){
            current_page = page_id;
            drawLinks();
            var continuePropagation = opts.callback(page_id+1, panel);
            if (!continuePropagation) {
                if (evt.stopPropagation) {
                    evt.stopPropagation();
                }
                else {
                    evt.cancelBubble = true;
                }
            }
            return continuePropagation;
        }

        /**
         * 此函数将分页链接插入到容器元素中
         */
        function drawLinks() {
            panel.empty();

            var ulTemp=jQuery("<ul></ul>");

            if(opts.ul_class){
                ulTemp.addClass(opts.ul_class);
            }

            var interval = getInterval();
            var np = numPages();
            // 这个辅助函数返回一个处理函数调用有着正确page_id的pageSelected。
            var getClickHandler = function(page_id) {
                return function(evt){ return pageSelected(page_id,evt); }
            }

            //页面跳转
            var pageSkip=function(){
                return function(evt){
                    var  inputVal=$(thisItem).find(".ski-input").val();
                    var reg = new RegExp("^[0-9]*$");
                    if(!reg.test(inputVal)){
                        inputVal=1;
                    }else{
                        if(inputVal>np)inputVal=np;
                        else if(inputVal<1) inputVal=1;
                    }
                    return  pageSelected(parseInt(inputVal)-1,evt);
                }
            }


            var  skipInpuKeyDown=function(event){
                return function(event){
                    if(event&& event.keyCode==13){ // enter 键
                        var  inputVal=$(thisItem).find(".ski-input").val();
                        var reg = new RegExp("^[0-9]*$");
                        if(!reg.test(inputVal)){
                            inputVal=1;
                        }else{
                            if(inputVal>np)inputVal=np;
                            else if(inputVal<1) inputVal=1;
                        }
                        return  pageSelected(parseInt(inputVal)-1,event);
                    }
                }
            }


            //辅助函数用来产生一个单链接(如果不是当前页则产生span标签)
            var appendItem = function(page_id, appendopts){
                page_id = page_id<0?0:(page_id<np?page_id:np-1); // 规范page id值
                appendopts = jQuery.extend({text:page_id+1, classes:""}, appendopts||{});
                if(page_id == current_page){
                    var lnk = jQuery("<li class='"+opts.selected_class+"'><a href='javaScript:void(0)'id='upPage'>"+(appendopts.text)+"</a></li>");
                }else{
                    var lnk = jQuery(" <li><a href='javaScript:void(0)' id='downPage'>"+(appendopts.text)+"</a></li>").bind("click", getClickHandler(page_id)).attr('href', opts.link_to.replace(/__id__/,page_id));
                }
                if(appendopts.classes){lnk.addClass(appendopts.classes);}
                if(appendopts.delClass){lnk.removeClass(appendopts.delClass);}

                ulTemp.append(lnk);
            }

             //首页
             if(opts.first_text){
                 var  first =jQuery("<li class='"+opts.first_class+"' >"+opts.first_text+"</li>").bind("click", getClickHandler(0));
                 ulTemp.append(first);
             }

            // 产生"Previous"-链接
            if(opts.prev_text && (current_page > 0 || opts.prev_show_always)){
                appendItem(current_page-1,{text:opts.prev_text, classes:opts.prev_class,delClass:opts.selected_class});
            }
            // 产生起始点
            if (interval[0] > 0 && opts.num_edge_entries > 0)
            {
                var end = Math.min(opts.num_edge_entries, interval[0]);
                for(var i=0; i<end; i++) {
                    appendItem(i);
                }
                if(opts.num_edge_entries < interval[0] && opts.ellipse_text)
                {
                    jQuery("<li><a href='javaScript:void(0)'  class='"+opts.ellipsis_class+"' >"+ opts.ellipse_text+"</a></li>").appendTo(ulTemp);
                }
            }
            // 产生内部的些链接
            for(var i=interval[0]; i<interval[1]; i++) {
                appendItem(i);
            }
            // 产生结束点
            if (interval[1] < np && opts.num_edge_entries > 0)
            {
                if(np-opts.num_edge_entries > interval[1]&& opts.ellipse_text)
                {
                    jQuery("<li><a href='javaScript:void(0)' class='"+opts.ellipsis_class+"' >"+ opts.ellipse_text+"</a></li>").appendTo(ulTemp);
                }
                var begin = Math.max(np-opts.num_edge_entries, interval[1]);
                for(var i=begin; i<np; i++) {
                    appendItem(i);
                }

            }
            // 产生 "Next"-链接
            if(opts.next_text && (current_page < np-1 || opts.next_show_always)){
                appendItem(current_page+1,{text:opts.next_text, classes:opts.next_class,delClass:opts.selected_class});
            }


            //末页
            if(opts.end_text){
                var  first =jQuery("<li class='"+opts.end_class+"' >"+opts.end_text+"</li>").bind("click", getClickHandler(np-1));
                ulTemp.append(first);
            }

            //页面跳转效果
             if(opts.skip_text){
                 var  skip=jQuery(opts.skip_container);
                 var  skiSpanClass="";
                 var  skipInput=jQuery("<input id='pageNum' type='text' class='ski-input "+opts.ski_input_class+"' value='"+(current_page+1)+"' />").bind("keydown",skipInpuKeyDown(window.event || arguments.callee.caller.arguments[0]));
                 var  skipButObject=jQuery("<a class='"+opts.ski_but_class+" ski-but' >"+opts.skip_text+"</a>'");
                 if("input_but"==opts.ski_but_theme){
                     skipButObject=jQuery("<input class='"+opts.ski_but_class+" ski-but' value='"+opts.skip_text+"'  />'");
                 }
                  skipButObject.bind("click",pageSkip());

                 skip.append("共").append(np).append("页&nbsp;&nbsp;&nbsp;去第 ").append(skipInput).append("页").append(skipButObject);
                 ulTemp.append(skip);
             }
             
             panel.append(ulTemp);

        }




        //从选项中提取current_page
        var current_page = opts.current_page-1<0?0:opts.current_page-1;
        //创建一个显示条数和每页显示条数值
        maxentries = (!maxentries || maxentries < 0)?1:maxentries;
        opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0)?1:opts.items_per_page;
        //存储DOM元素，以方便从所有的内部结构中获取
        var panel = jQuery(this);
        // 获得附加功能的元素
        this.selectPage = function(page_id){ pageSelected(page_id);}
        this.prevPage = function(){
            if (current_page > 0) {
                pageSelected(current_page - 1);
                return true;
            }
            else {
                return false;
            }
        }
        this.nextPage = function(){
            if(current_page < numPages()-1) {
                pageSelected(current_page+1);
                return true;
            }
            else {
                return false;
            }
        }
        // 所有初始化完成，绘制链接
        drawLinks();
        // 回调函数
      //  opts.callback(current_page, this);
    });
}


