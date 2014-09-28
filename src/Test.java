import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class Test {
	public static class WsClient {

		private RPCServiceClient serviceClient;
		private Options options;
		private EndpointReference targetEPR;

		public WsClient(String endpoint) throws AxisFault {
			serviceClient = new RPCServiceClient();
			options = serviceClient.getOptions();
			targetEPR = new EndpointReference(endpoint);
			options.setTo(targetEPR);
		}

		public Object[] invokeOp(String targetNamespace, String opName,
				Object[] opArgs, Class<?>[] opReturnType) throws AxisFault,
				ClassNotFoundException {
			// 设定操作的名称
			QName opQName = new QName(targetNamespace, opName);
			// 设定返回值

			// Class<?>[] opReturn = new Class[] { opReturnType };

			// 操作需要传入的参数已经在参数中给定，这里直接传入方法中调用
			return serviceClient.invokeBlocking(opQName, opArgs, opReturnType);
		}

		/**
		 * @param args
		 * @throws AxisFault
		 * @throws ClassNotFoundException
		 */
		public static void main(String[] args) throws AxisFault, ClassNotFoundException{
			// http://localhost:9999/WebServiceDemo/services/MyService/addUser?name=1
			//http://localhost:9999/WebServiceDemo/services/MyService?wsdl
			//http://localhost:8888/LOFC/services/axis/addUser?name=1
//			final String endPointReference = "http://localhost:8888/Axis/services/MyService";
			final String endPointReference = "http://localhost:8888/LOFC/services/axis";
			final String targetNamespace = "http://process.action.financial.godfish.com";
			//targetNamespace的内容，访问//http://localhost:9999/WebServiceDemo/services/MyService?wsdl的时候，看targetNamespace的内容
			WsClient client = new WsClient(endPointReference);
			String opName = "addUser";
			Object[] opArgs = new Object[] { "dddd" };
			Class<?>[] opReturnType = new Class[] { String[].class };

			Object[] response = client.invokeOp(targetNamespace, opName,
					opArgs, opReturnType);
			System.out.println(((String[]) response[0])[0]);
		}

	}
}