package nms.tools.services;

public enum RpcCommands {

	
	VERSION("Version.Version"),
	LIST_PORTS("EthFace.ListPorts"),
	GET_FACE("Face.Get"),
	LIST_FACES("Face.List"),
	CREATE_FACE("Face.Create"),
	DESTROY_FACE("Face.Destroy"),
	LIST_FIB("Fib.List"),
	ADD_ROUTE("Route.Add"),
	REMOVE_ROUTE("Route.Remove");
	;
	
private String name;

	RpcCommands(String name) {
		this.name = name;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
