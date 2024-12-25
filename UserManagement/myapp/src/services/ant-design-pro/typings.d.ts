// @ts-ignore
/* eslint-disable */

declare namespace API {
  type CurrentUser = {
        /**
         * 主键
         */
        id: number;
    
        /**
         * 昵称
         */
        username: string;
    
        /**
         * 头像
         */
        avatarUrl?: string;
    
        /**
         * 性别
         */
        gender: number;
    
        /**
         * 电话
         */
        phone?: string;
    
        /**
         * 邮箱
         */
        email: string;
    
        /**
         * 是否有效 (比如被封号之类的)
         */
        isValid: number;
    
        /**
         * 创建时间 (数据插入时间)
         */
        createtime: Date;
    
        /**
         * 更新时间 (数据更新时间)
         */
        updatetime: Date;
    
        /**
         * 是否删除 0:否, 1:是 (逻辑删除)
         */
        isDelete: number;
    
        /**
         * 星球code
         */
        roleId: number;

         /**
         * 用户角色 0代表普通用户 1代表vip会员 2代表超级管理员
         */
         planetCode: string;
  };   

  type LoginResult = {
    status?: string;
    type?: string;
    currentAuthority?: string;
  };

  type registerResult = {
    status?: string;
  };

  type PageParams = {
    current?: number;
    pageSize?: number;
  };

  type RuleListItem = {
    key?: number;
    disabled?: boolean;
    href?: string;
    avatar?: string;
    name?: string;
    owner?: string;
    desc?: string;
    callNo?: number;
    status?: number;
    updatedAt?: string;
    createdAt?: string;
    progress?: number;
  };

  type RuleList = {
    data?: RuleListItem[];
    /** 列表的内容总数 */
    total?: number;
    success?: boolean;
  };

  type FakeCaptcha = {
    code?: number;
    status?: string;
  };

  type LoginParams = {
    username?: string;
    password?: string;
    autoLogin?: boolean;
    type?: string;
  };

  type RegisterParams = {
    username?: string;
    password?: string;
    checkPassword?: string;
    planetCode?: string;
    type?: string;
  };

  type LoginBody = {
    username?: string;
    password?: string;
  };

  type ErrorResponse = {
    /** 业务约定的错误码 */
    errorCode: string;
    /** 业务上的错误信息 */
    errorMessage?: string;
    /** 业务上的请求是否成功 */
    success?: boolean;
  };

  type NoticeIconList = {
    data?: NoticeIconItem[];
    /** 列表的内容总数 */
    total?: number;
    success?: boolean;
  };

  type NoticeIconItemType = 'notification' | 'message' | 'event';

  type NoticeIconItem = {
    id?: string;
    extra?: string;
    key?: string;
    read?: boolean;
    avatar?: string;
    title?: string;
    status?: string;
    datetime?: string;
    description?: string;
    type?: NoticeIconItemType;
  };

    /**
 * 通用返回体
 */
interface BaseResponse<T> {
  code: number;
  data: T;
  message: string;
  description: string;
};
}
