 $(document).ready(function() {
            /* Select link with an id of first and a class of big.*/
            $("ul li:first").css({"background-color":"rgb(201, 198, 210,0.4)","border-bottom-right-radius": "40px","border-top-right-radius": "40px"});
         });
$(function () {
    setNavigation();
});

function setNavigation() {
    var path = window.location.pathname;
    path = path.replace(/\/$/, "");
    path = decodeURIComponent(path);

    $("#side1 a").each(function () {
        var href = $(this).attr('href');
        if (path.substring(0, href.length) === href) {
         $("ul li:first").css({"background-color":"", "border-bottom-right-radius": "", "border-top-right-radius": ""});

            $(this).closest('li').addClass('active');
//                        $(this).parent().addClass('active');
//jQuery(this).addClass("active");

           
        
//    });
}
    });

//jQuery(document).ready(function($) {  
//    jQuery("*").find("a[href='"+window.location.href+"']").each(function(){    
//        jQuery(this).addClass("active");
//})        //add additional code here if needed    })}); 
//        });
}
