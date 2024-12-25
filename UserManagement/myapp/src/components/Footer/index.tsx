import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import React from 'react';

const Footer: React.FC = () => {
  const defaultMsg:string = 'Copyright © 2021 GuwangJUEcheer. All Rights Reserved';
  return (
    <DefaultFooter
      copyright={defaultMsg}
      style={{
        background: 'none',
      }}
      links={[
        {
          key: 'planet',
          title: '知识星球',
          href: 'https://pro.ant.design',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/GuwangJUEcheer',
          blankTarget: true,
        },
        {
          key: 'homepage',
          title: '编程导航',
          href: 'https://ant.design',
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
