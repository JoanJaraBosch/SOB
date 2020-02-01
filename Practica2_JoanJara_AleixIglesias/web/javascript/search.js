/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
   $(window).scroll(function(){
       if($(this).scrollTop()>0){
           $('.header').addClass('header2');
       }else{
           $('.header').removeClass('header2');
       }
   }) ;
});