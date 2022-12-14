(function(){
    //lnb
    /*const _lnb = document.querySelectorAll("#lnb > ul > li");
    _lnb.forEach(function(el){
        el.addEventListener("click", e => {
            let _subMenu = document.querySelectorAll("#lnb > ul > li > .subMenu");
            let _lnbSel = document.querySelector("#lnb ul .on");
            if(_lnbSel !== null){
                _lnbSel.classList.remove("on");
            }

            _subMenu.forEach(s =>{
                if(s !== null){
                    s.style.display = "none";
                }
            });

            let _sm = e.currentTarget.querySelector(".subMenu");
            if(_sm !== null){
                e.target.classList.add("on");
                _sm.style.display = "block";
            }
        })
    })*/

    $(document).on("click", "#lnb > ul > li", function(e){
		if(e.target == e.currentTarget){
			if($(this).hasClass("on") === true){
				e.target.classList.remove("on");
				
				if($(this).children(".subMenu").length > 0){
					$(this).children(".subMenu")[0].style.display = "none";
				}
				
			}else{
				e.target.classList.add("on");
				
				if($(this).children(".subMenu").length > 0){
					$(this).children(".subMenu")[0].style.display = "block";
				}
			}
		}
	});
	
	

    //popup dimmed
    /*let dim = document.querySelector(".mask");
    let close = document.querySelectorAll(".pop-close");

    if(dim !== null){
        dim.addEventListener("click", popClose);
    }
    if(close !== null){
        close.forEach(e => {s
            e.addEventListener("click", popClose);
        })
    }*/

    // popup close
    function popClose(){
        let laypop = document.querySelectorAll(".layerPopup");
        let layArt = document.querySelectorAll(".layerAlert");
        dim.style.display = "none";
        if(laypop !== null){
            laypop.forEach(e => {
                e.style.display = "none";
            })
        }
        if(layArt !== null){
            layArt.forEach(e => {
                e.style.display = "none";
            })
        }
    }
    // parentElement
    //tab
    _tabMenu = document.querySelectorAll(".tab > ul > li");
    if(_tabMenu !== null){
        _tabMenu.forEach(el => {
            el.addEventListener("click", e => {
                let _tabSel = document.querySelector(".on")
                _tabSel.classList.remove("on");
                
                e.target.parentElement.classList.add("on");
                // this.className = 'd';

                let idx = Array.from(_tabMenu).indexOf(document.querySelector(".tab ul li.on"));
                let tabBox = document.querySelectorAll(".tabBox");
                console.log(idx);

                tabBox.forEach(e => {
                    e.style.display = "none";
                })

                tabBox[idx].style.display = "block";

                console.log(_tabMenu[idx]);
            })
        })
    }

    // asc & desc
    _tbHeadSort = document.querySelectorAll(".sort");
    _tbHeadSort.forEach(el => {
        el.addEventListener("click", e=>{
            if(e.target.classList.contains('asc')){
                e.target.classList.remove("asc");
            }else{
                e.target.classList.add("asc");
            }
        })
    })

    // 옵션 이동
    let _opBtnRemove = document.querySelector(".bt-rmv");
    let _opBtnPut = document.querySelector(".bt-put");
    let _optionBox = document.querySelector(".opApdBox");
    let _selList = document.querySelector(".selListBox .sel-b");

    if(_opBtnPut){
        _opBtnPut.addEventListener("click", e => {
            let _opCheckedEl = _selList.querySelectorAll("input:checked");
            let htmlBox = "";

            if(_opCheckedEl.length === 0){
                alert("There are no option to move.");
                return;
            }

            _opCheckedEl.forEach((e, i, a) =>{
                console.log(e.value);
                console.log(e.id);

                htmlBox += " <input type='checkbox' class='op-i-check' id='opitem" + i + "' value='" + e.id + "'>";
                htmlBox += "<label class='op-item' for='opitem" + i + "'>" + e.value + "</label>";

                e.disabled = true;
            })
            _optionBox.innerHTML = htmlBox;
        })
    }

    if(_opBtnRemove){
        _opBtnRemove.addEventListener("click", el => {
            let _optionRmvEl =  _optionBox.querySelectorAll("input:checked");

            if(_optionRmvEl.length === 0){
                alert("There are no option to move or delete");
                return;
            }

            _optionRmvEl.forEach(e => {
                e.parentNode.removeChild(e.nextSibling);
                e.parentNode.removeChild(e);

                _selList.querySelector("#" + e.value +"").disabled = false;
                _selList.querySelector("#" + e.value +"").checked = false;


            })
        })
    }
})();
    
// layer popup
function layerPopup(id){
    let dim = document.querySelector(".mask");
    let pop = document.querySelector("#" + id);
    pop.style.display = "block";
    pop.style.marginLeft = "-" + pop.offsetWidth/2 + "px";
    dim.style.display = "block";
}

function mvPage(url, param){
	
	var param = {
		  "url"         : url
		, "contentType" : "form"
		, "data"        : {"menuParam" : param}

	};
	AjaxUtil.send(param);
}
