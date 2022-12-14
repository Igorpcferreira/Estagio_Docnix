
//Função para validar o cpf de acordo com a receita federal
function _cpf(cpf) {
    cpf = cpf.replace(/[^\d]+/g, '');
    if (cpf == '') return false;
    if (cpf.length != 11 ||
       cpf == "00000000000" ||
       cpf == "11111111111" ||
       cpf == "22222222222" ||
       cpf == "33333333333" ||
       cpf == "44444444444" ||
       cpf == "55555555555" ||
       cpf == "66666666666" ||
       cpf == "77777777777" ||
       cpf == "88888888888" ||
       cpf == "99999999999")
       return false;
    add = 0;
    for (i = 0; i < 9; i++)
       add += parseInt(cpf.charAt(i)) * (10 - i);
    rev = 11 - (add % 11);
    if (rev == 10 || rev == 11)
       rev = 0;
    if (rev != parseInt(cpf.charAt(9)))
       return false; 
    add = 0;
    for (i = 0; i < 10; i++)
       add += parseInt(cpf.charAt(i)) * (11 - i);
    rev = 11 - (add % 11);
    if (rev == 10 || rev == 11)
       rev = 0;
    if (rev != parseInt(cpf.charAt(10)))
       return false;
    return true;
}
function validarCPF(el){
    if( !_cpf(el.value) ){
        alert("CPF inválido! \n erro: " + el.value);
    
        // apaga o valor
        el.value = "";
    }
}

//Máscara telefone
function mascaraTelefone(o,f){
   v_obj=o
   v_fun=f
   setTimeout("execmascara()",1)
}
function execmascara(){
   v_obj.value=v_fun(v_obj.value)
}
function mtel(v){
   v=v.replace(/\D/g,""); //Remove tudo o que não é dígito
   v=v.replace(/^(\d{2})(\d)/g,"($1) $2"); //Coloca parênteses em volta dos dois primeiros dígitos
   v=v.replace(/(\d)(\d{4})$/,"$1-$2"); //Coloca hífen entre o quarto e o quinto dígitos
   return v;
}
function id( el ){
  return document.getElementById( el );
}
window.onload = function(){
  id('telefone').onkeyup = function(){
     mascaraTelefone( this, mtel );
  }
}


//Máscara cpf
function mascaraCpf(i){
   
   var v = i.value;
   
   if(isNaN(v[v.length-1])){ // impede entrar outro caractere que não seja número
      i.value = v.substring(0, v.length-1);
      return;
   }
   
   i.setAttribute("maxlength", "14");
   if (v.length == 3 || v.length == 7) i.value += ".";
   if (v.length == 11) i.value += "-";

}
