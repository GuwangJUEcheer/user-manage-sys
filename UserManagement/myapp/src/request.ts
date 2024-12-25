// src/utils/request.ts
import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";
import { notification } from "antd"; // 用于错误提示

// 定义响应数据的通用接口
interface ResponseData<T = any> {
  code: number; // 自定义返回码
  message: string; // 消息提示
  data: T; // 响应数据
}

// 创建 axios 实例
const service: AxiosInstance = axios.create({
  baseURL: "http://localhost:8080", // 设置为本地服务器地址
  timeout: 5000, // 请求超时时间
  withCredentials: true, // 允许携带跨域 cookies
});

// 请求拦截器
service.interceptors.request.use(
  (config: AxiosRequestConfig) => {  
    config.headers = {
      ...config.headers,
      "Content-Type": "application/json", // 默认设置为 JSON
    };
    return config;
  },
  (error) => {
    // 处理请求错误
    console.error("请求错误", error);
    return Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (response: AxiosResponse<ResponseData>) => {
    const res = response.data;
    // if (res.code !== 200) {
    //   // 根据实际 API 的返回码处理
    //   notification.error({
    //     message: `请求错误 ${res.code}`,
    //     description: res.message || "服务器发生错误，请稍后重试。",
    //   });
    //   return Promise.reject(new Error(res.message || "Error"));
    // }
    return res.data; // 返回数据部分
  },
  (error) => {
    // 处理响应错误
    if (error.response) {
      const { status, data } = error.response;
      // 提示错误信息
      notification.error({
        message: `请求错误 ${status}`,
        description: data?.message || "服务器发生错误，请稍后重试。",
      });
    } else {
      notification.error({
        message: "网络错误",
        description: "无法连接到服务器，请检查网络设置。",
      });
    }
    return Promise.reject(error);
  }
);

export default service;
