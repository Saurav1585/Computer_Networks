import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class reciver {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",5000);
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        String total="",str2="";
        String divisor = new String();
        divisor = "1001";
        int count=0, p=0;
	while(p<4)
	{  
		total=din.readUTF();
		
		int[] d= new int[4*7+4];
                int z[],r[],msb,i,j,k;
                int[] g = new int[4];
                int n = 28;
                int m=4;
                for(i=0;i<n+m-1;i++) {
                    d[i] = Character.getNumericValue(total.charAt(i));
                }
                for(j=0;j<m;j++) {
                    g[j]=Character.getNumericValue(divisor.charAt(j));
                }
                r=new int[m+n];
                for(i=0;i<m;i++)
                    r[i]=d[i];
                z=new int[m];
                for(i=0;i<m;i++)
                    z[i]=0;
                for(i=0;i<n;i++)
                {
                    k=0;
                    msb=r[i];
                    for(j=i;j<m+i;j++)
                    {
                        if(msb==0)
                            r[j]=xor(r[j],z[k]);
                        else
                            r[j]=xor(r[j],g[k]);
                        k++;
                    }
                    r[m+i]=d[m+i];
                }
                System.out.print("The code bits added are : ");
                for(i=n;i<n+m-1;i++)
                {
                    d[i]=r[i];
                    System.out.print(d[i]);
                }
                System.out.print("\nThe code data is : ");
                for(i=0;i<n+m-1;i++)
                {
                    System.out.print(d[i]);
                }
		
		if(d[n]==0&&d[n+1]==0&&d[n+2]==0)
		{  
			str="OK"; 
			dout.writeUTF(str);  
			dout.flush();
			System.out.print("\n Frame "+p+1+" send "+count+" times.\n");
			p++;
			count=0;
		}
		else
		{  
			str="NAK";
			dout.writeUTF(str);  
			dout.flush();
			count++;
		}
	}  
  
	dout.close();  
	s.close();  
    }
    public static int xor(int x,int y)
    {
        if(x==y)
            return(0);
        else
            return(1);
    }
}
