var _dwtParam = {
'productKey': 'E28C2E7C258C95D3821F103B93EA0A26C6514ED2E3B87F97AD92D9023CBF7F79C6514ED2E3B87F97CA0A4ACBC5F502A4C6514ED2E3B87F9735370B0BAC9460DF30000000',
    'containerID': 'dwtcontrolContainer',   //The ID of Dynamic Web TWAIN control div in HTML.This value is required.
    /*
    'isTrial': 'true',  
    isTrial is used to judge whether Dynamic Web TWAIN control is trial or full. This value is optional.
    The default value is 'TRUE', which means the control is a trial one. The value of isTrial is 'TRUE' or 'FALSE'.
    */

    /*
    'version': '9,2',   
    The version of Dynamic Web TWAIN control, which is used to judge the version when downloading CAB.
    This value is optional. The default value is '9,2'.
    */

    /*
    'resourcesPath': 'Resources',   
    The relative path of MSI, CAB and PKG.
    This value is optional. The default value is 'Resources'.
    */

    /*
    'width': 580,       //The width of Dynamic Web TWAIN control
    This value is optional. The default value is '580'.
    */

    /*
    'height': 600       //The height of  Dynamic Web TWAIN control
    This value is optional. The default value is '600'.
    */

    /*  These are events. The name of 'OnPostAllTransfer' shouldn't be changed, but the name of 'Dynamsoft_OnPostAllTransfers' can be modified. 
    Please pay attention, the name of 'Dynamsoft_OnPostAllTransfers' and 'function Dynamsoft_OnPostAllTransfers()' event must be coincident.
        
    Events are as follows. You can choose one or many according to you needs.
    Once an event is added, you must make sure the corresponding function is defined in your code.
        
    'onPostTransfer':Dynamsoft_OnPostTransfer,
    'onPostAllTransfers':Dynamsoft_OnPostAllTransfers,  
    'onMouseClick':Dynamsoft_OnMouseClick,   
    'onPostLoad':Dynamsoft_OnPostLoadfunction,    
    'onImageAreaSelected':Dynamsoft_OnImageAreaSelected,   
    'onMouseDoubleClick':Dynamsoft_OnMouseDoubleClick,   
    'onMouseRightClick':Dynamsoft_OnMouseRightClick,   
    'onTopImageInTheViewChanged':Dynamsoft_OnTopImageInTheViewChanged,   
    'onImageAreaDeSelected':Dynamsoft_OnImageAreaDeselected,    
    'onGetFilePath':Dynamsoft_OnGetFilePath  
    */  
     'onTopImageInTheViewChanged':Dynamsoft_OnTopImageInTheViewChanged                            
};


var gWebTwain;
(function() {
	gWebTwain = new Dynamsoft.WebTwain(_dwtParam);
})();

var seed;
function onPageLoad() {
 
    seed = setInterval(initControl, 500);
}


function initControl() {
    var DWObject = gWebTwain.getInstance();
    if (DWObject) {
        if (DWObject.ErrorCode == 0) {
            clearInterval(seed);
            DWObject.BrokerProcessType = 1;
        }
    }
}

function acquireImage() {
    var DWObject = gWebTwain.getInstance();
    if (DWObject) {
        if (DWObject.SourceCount > 0) {
            DWObject.SelectSource();
            DWObject.AcquireImage();
        }
        else
            alert("No TWAIN compatible drivers detected.");
    }
}

function Dynamsoft_OnTopImageInTheViewChanged(index) {
    var DWObject = gWebTwain.getInstance();
    if (DWObject) {
        DWObject.CurrentImageIndexInBuffer = index;
    }
}

//--------------------------------------------------------------------------------------
//************************** Upload Image***********************************
//--------------------------------------------------------------------------------------

function btnUpload_onclick() {
    var DWObject = gWebTwain.getInstance();
    if (DWObject) {
        if (DWObject.HowManyImagesInBuffer == 0) {
            return;
        }
      var  varFileName = document.getElementById("txtFileName");
    
        strImageType=1;
        var strFTPServer = document.getElementById("txtFTPServer").value;
        DWObject.FTPPort = document.getElementById("txtFTPPort").value;
        var varUserName = document.getElementById("txtUserName");
        if (varUserName) {
            if (varUserName.value != "") {
                DWObject.FTPUserName = varUserName.value;

                var varPassword = document.getElementById("txtPassword");
                if (varPassword) {
                    DWObject.FTPPassword = varPassword.value;
                }
            }
            else {
                DWObject.FTPUserName = "";
                DWObject.FTPPassword = "";
            }
        }

            var uploadFullPath = varFileName.value + ".jpg";
 
            DWObject.FTPUploadEx(
            strFTPServer,
            DWObject.CurrentImageIndexInBuffer,
            uploadFullPath,
            strImageType);
  
        
        alert(DWObject.ErrorString);
    }
}
