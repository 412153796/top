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
			// �趨����������
			QName opQName = new QName(targetNamespace, opName);
			// �趨����ֵ

			// Class<?>[] opReturn = new Class[] { opReturnType };

			// ������Ҫ����Ĳ����Ѿ��ڲ����и���������ֱ�Ӵ��뷽���е���
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
			//targetNamespace�����ݣ�����//http://localhost:9999/WebServiceDemo/services/MyService?wsdl��ʱ�򣬿�targetNamespace������
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