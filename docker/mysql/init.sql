CREATE DATABASE IF NOT EXISTS blog_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE blog_db;

-- 管理员表
CREATE TABLE IF NOT EXISTS admin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    nickname VARCHAR(50),
    avatar VARCHAR(255),
    email VARCHAR(100),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 个人介绍表
CREATE TABLE IF NOT EXISTS personal_intro (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    content TEXT,
    welcome_text VARCHAR(500),
    avatar VARCHAR(255),
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 技术栈表
CREATE TABLE IF NOT EXISTS tech_stack (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    icon VARCHAR(255),
    description VARCHAR(200),
    sort_order INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 项目表
CREATE TABLE IF NOT EXISTS project (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    tech_stack VARCHAR(255),
    github_url VARCHAR(255),
    demo_url VARCHAR(255),
    image VARCHAR(255),
    sort_order INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 分类表
CREATE TABLE IF NOT EXISTS category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(200),
    sort_order INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 文章表
CREATE TABLE IF NOT EXISTS article (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    summary VARCHAR(500),
    cover_image VARCHAR(255),
    category_id BIGINT,
    is_published BOOLEAN DEFAULT FALSE,
    view_count INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入默认管理员 (密码: admin123)
INSERT INTO admin (username, password, nickname, email) VALUES
('admin', 'admin123', 'Brix', 'admin@brix.com');

-- 插入默认个人介绍
INSERT INTO personal_intro (title, content, welcome_text) VALUES
('关于我', '我是Brix，一个热爱编程的全栈工程师。', '欢迎来到我的博客，在这里你可以看到一个喜欢学习，热爱编程的少年，快去探索吧～');

-- 插入默认技术栈
INSERT INTO tech_stack (name, description, sort_order) VALUES
('Java', '后端开发语言', 1),
('AI Agent', '人工智能代理', 2),
('Spring', 'Java框架', 3),
('Linux', '操作系统', 4),
('Git', '版本控制', 5),
('MySQL', '关系型数据库', 6),
('Docker', '容器化技术', 7),
('JavaScript', '前端开发语言', 8),
('Vue', '前端框架', 9);

-- 插入默认项目
INSERT INTO project (name, description, tech_stack, sort_order) VALUES
('TS教学管理系统', '一个针对师生教学统筹系统化的管理系统', 'Java, Spring Boot, MySQL', 1),
('Runner酷跑游戏', '一个基于JavaFX的2D横版跑酷游戏项目', 'Java, JavaFX', 2),
('NFT代币铸造工具', '一个基于区块链设计的非同质化代币铸造工具', 'Solidity, Web3.js', 3),
('Fabric联盟链房屋交易系统', '一个基于 Hyperledger Fabric 的房地产联盟链交易平台', 'Hyperledger Fabric, Node.js', 4),
('code-review-agent', '一个基于多Agent协作的自动化代码审查系统', 'Python, AI', 5),
('website-reader', '一个基于AI的网站分析工具，输入任意网站URL，自动从多个维度进行全面分析', 'Python, AI', 6);

-- 插入默认分类
INSERT INTO category (name, description, sort_order) VALUES
('技术分享', '技术相关文章', 1),
('项目实战', '项目开发经验', 2),
('学习笔记', '学习过程记录', 3),
('生活随笔', '日常生活感悟', 4);
