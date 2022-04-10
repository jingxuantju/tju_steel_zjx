package tju.steel.zjx.service.impl;

import tju.steel.zjx.entity.ParamInfo;
import tju.steel.zjx.mapper.ParamInfoMapper;
import tju.steel.zjx.service.ParamInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zjx
 * @since 2021-06-25
 */
/**
 * 类功能：ParamInfoService 的实现
 */
@Service
public class ParamInfoServiceImpl extends ServiceImpl<ParamInfoMapper, ParamInfo> implements ParamInfoService {

    /**
     * 向检测端传送型钢规格 端口：8120
     * @param specification 型钢型号
     */
    @Override
    public void transformSpecificationInfo(String specification) {

        // 创建 socket、主机地址与端口号
        Socket socket = null;
        String destAddress = "127.0.0.1";
        int destPort = 8120;

        try {
            socket = new Socket(destAddress, destPort);

            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];

            outputStream.write(specification.getBytes());
            int len = inputStream.read(bytes);
            String str = new String(bytes,0,len);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向检测端传送型钢长度 端口：8130
     * @param length    型钢长度
     */
    @Override
    public void transformLengthInfo(String length) {

        Socket socket = null;
        String destAddress = "127.0.0.1";
        int destPort = 8130;

        try {
            socket = new Socket(destAddress, destPort);

            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];

            outputStream.write(length.getBytes());
            int len = inputStream.read(bytes);
            String str = new String(bytes,0,len);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向采集端传送型钢型号 端口：6666
     * @param specification
     */
    @Override
    public void transformSpecificationToCPP(String specification) {

        Socket socket = null;
        String destAddress = "127.0.0.1";
        int destPort = 6666;

        // 需要传送的编号
        String type = "0";
        if (specification.equals("240*240") || specification.equals("250*250") || specification.equals("250*255") ||
                specification.equals("260*260") || specification.equals("270*248") || specification.equals("280*280") ||
                specification.equals("290*268") || specification.equals("300*300") || specification.equals("300*305") ||
                specification.equals("350*350") || specification.equals("350*357") || specification.equals("390*300") ||
                specification.equals("400*400")) {
            type = "1";
        }
        else if (specification.equals("414*405") || specification.equals("440*300") || specification.equals("450*200") ||
                specification.equals("482*300") || specification.equals("488*300") || specification.equals("496*199") ||
                specification.equals("500*200") || specification.equals("582*300") || specification.equals("588*300") ||
                specification.equals("594*302") || specification.equals("596*199") || specification.equals("600*200") ||
                specification.equals("606*201")) {
            type = "2";
        }
        else {
            type = "3";
        }

        try {
            socket = new Socket(destAddress, destPort);

            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[2];

            outputStream.write((type + "\0").getBytes());
            int len = inputStream.read(bytes);
            String str = new String(bytes,0,len);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
