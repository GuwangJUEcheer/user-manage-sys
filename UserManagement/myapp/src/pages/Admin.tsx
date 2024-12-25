import React, { PropsWithChildren } from 'react';
import PageHeaderWrapper from '@ant-design/pro-layout';
import { useIntl } from '@umijs/max';
import { Alert, Card, Typography } from 'antd';


const Admin: React.FC = (props:PropsWithChildren<{}>) => {
  const {children} = props;
  const intl = useIntl();
  return (
    <PageHeaderWrapper>
      {children}
    </PageHeaderWrapper>
  );
};

export default Admin;
