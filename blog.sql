/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.20 : Database - db_blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_blog`;

/*Table structure for table `t_blog` */

DROP TABLE IF EXISTS `t_blog`;

CREATE TABLE `t_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `keyword` varchar(256) DEFAULT NULL COMMENT '关键字',
  `summary` varchar(512) DEFAULT NULL COMMENT '摘要',
  `content` varchar(2048) DEFAULT NULL COMMENT '内容',
  `releaseDate` datetime DEFAULT NULL COMMENT '发表时间',
  `clickHit` int(11) DEFAULT NULL COMMENT '点击数',
  `replyHit` int(11) DEFAULT NULL COMMENT '评论数',
  `typeId` int(11) DEFAULT NULL COMMENT '所属博客类型',
  PRIMARY KEY (`id`),
  KEY `typeId` (`typeId`),
  CONSTRAINT `t_blog_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `t_blogtype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='博客表';

/*Data for the table `t_blog` */

insert  into `t_blog`(`id`,`title`,`keyword`,`summary`,`content`,`releaseDate`,`clickHit`,`replyHit`,`typeId`) values (1,'计算机操作系统的发展趋势探究','计算机 操作系统 计算机技术 ','进入新时期后,计算机信息技术迅速发展,且在各行各业得到了十分广泛的运用,推动了各个领域的发展。计算机技术的一项重要组成为操作系统,其具有多样化的功能,如环境建设、系统管理和协调运行等。虽然目前计算机操作系统已经逐渐完善,但是随着科学技术的革新及人们需求的增加,未来计算机操作系统将会获得进一步的发展。本文简要介绍了计算机操作系统的应用与发展趋势,希望能够提供一些有价值的参考意见。 ','<p>目前,互联网发展已经达到了鼎盛阶段,在人们的生活中,互联网发挥着不可替代的作用。大数据技术在互联网技术应用中也非常重要,二者相互依托。本文分析数据库技术在大数据中的应用,从而更好的促进互联网技术的发展[…]</p><p><br/></p><p><br/></p>','2019-11-13 20:51:11',4,0,21),(5,'数据库技术在大数据中的应用探讨','大数据 数据库集群技术 分布集群 ','互联网技术发展非常惊人,大量的数据产生。在云计算高速发展的今天,大数据结合数据库集群技术,提升了数据处理的效率。','<p>目前,互联网发展已经达到了鼎盛阶段,在人们的生活中,互联网发挥着不可替代的作用。大数据技术在互联网技术应用中也非常重要,二者相互依托。本文分析数据库技术在大数据中的应用,从而更好的促进互联网技术的发展[…]</p><p><br/></p>','2019-11-13 21:08:11',6,0,25),(6,'基于计算机软件开发的JAVA编程语言研究','二叉树 层次 队列 链式结构 ','利用数组存放顺序结构任意二叉树会存在大量空节点,造成空间浪费,所以链式结构是存储任意二叉树的最佳方式.队列结构是计算链式结构任意二叉树层次问题的一种较好方法.通过对算法设计的思路和程序进行分析,给出了算法实现的路径. ','<p>在数据结构中, 树是一种用途广泛的结构.二叉树是每个节点最多有两个子树的树结构.在对任意二叉树进行存储的时候, 普遍采用链式结构存储数据, 在对链式结构存储的任意二叉树进行操作的时候, 常采用递归的方[…]</p><p><br/></p>','2019-11-15 19:30:49',18,0,1),(8,'基于队列的任意二叉树层次问题算法设计','二叉树 层次  队列  链式结构 ','利用数组存放顺序结构任意二叉树会存在大量空节点,造成空间浪费,所以链式结构是存储任意二叉树的最佳方式.队列结构是计算链式结构任意二叉树层次问题的一种较好方法.通过对算法设计的思路和程序进行分析,给出了算法实现的路径. ','<p><span style=\"text-align: justify; color: rgb(51, 51, 51); font-family: &quot;Microsoft Yahei&quot;; background-color: rgb(255, 255, 255);\">利用数组存放顺序结构任意二叉树会存在大量空节点,造成空间浪费,所以链式结构是存储任意二叉树的最佳方式.队列结构是计算链式结构任意二叉树层次问题的一种较好方法.通过对算法设计的思路和程序进行分析,给出了算法实现的路径.&nbsp;</span></p>','2019-11-15 20:29:43',4,0,5),(9,'Java信息管理系统开发模式设计','JAVA 信息管理系统 开发模式 设计','在社会经济和科学技术迅猛发展的过程中,对企业管理提出了更大的要求,必须对其管理的科学化和规范化做到足够的重视,要实现此目标,需要将管理系统的开发作为重中之重。对B/S结构特点和JAVA的特性进行充分的应用,开展MVC架构的Web应用程序的设计,并且保证其合理性和科学性,在当下企业实现其科学管理过程中,占据着至关重要的位置,是其关键和核心。本文对将数据作为中心的JAVA信息系统开发模式设计进行了比较全面的阐述。','<p><span style=\"text-align: justify; color: rgb(51, 51, 51); font-family: &quot;Microsoft Yahei&quot;; background-color: rgb(255, 255, 255);\">在社会经济和科学技术迅猛发展的过程中,对企业管理提出了更大的要求,必须对其管理的科学化和规范化做到足够的重视,要实现此目标,需要将管理系统的开发作为重中之重。对B/S结构特点和JAVA的特性进行充分的应用,开展MVC架构的Web应用程序的设计,并且保证其合理性和科学性,在当下企业实现其科学管理过程中,占据着至关重要的位置,是其关键和核心。本文对将数据作为中心的JAVA信息系统开发模式设计进行了比较全面的阐述。</span></p>','2019-11-15 20:38:18',8,0,16),(10,'基于数据结构的单向链表排序算法探究','数据结构 链表 插入排序  时间复杂度  空间复杂度 ','链表是一种较为复杂的数据结构,而基于链表的排序算法更是让人难以理解,且普遍效率较低,但其运用却极其广泛。通过对基于单向链表的插入排序算法进行剖析,继而归纳出其与顺序存储结构上实现插入排序算法的区别与优势,并从时间复杂度、空间复杂度与稳定性进行比较,体现出其优越性能和实现技巧。 ','<p>链表是数据结构中一个重要组成部分, 其应用非常广泛。 但由于链表的存储方式及其算法均较为复杂, 所以学生在学习的过程中不容易理解。 通过对单向链表来实现一种排序算法, 进一步阐述链表的实际应用技巧和方[…]</p><p><br/></p>','2019-11-15 21:20:20',8,1,5),(11,'光微流激光免疫比浊法研究','光微流激光 免疫比浊法 抗原抗体复合物 免疫检测 无标记生化检测 ','基于抗原抗体分子识别的免疫学分析方法因操作简单、检测时间快、检测灵敏度高及特异性好等优势,在临床诊断、环境监测及食品安全检测等领域获得了广泛的关注和研究。免疫分析法是借助抗原抗体的特异性免疫识别测定样品中对应抗原或抗体的浓度,免疫比浊法属于无标记免疫分析。无标记免疫分析无需事先对抗原或抗体进行标记,具有检测步骤少、操作简单、检测时间快、成本较低等优点。但是,现有的无标记免疫分析方法的检测灵敏度不够高,限制了它的使用范围。本文结合光微流技术提出了一种光微流激光免疫比浊法。','<p><span id=\"ChDivSummary\" style=\"margin: 0px; padding: 0px; text-align: justify; color: rgb(51, 51, 51); font-family: &quot;Microsoft Yahei&quot;; background-color: rgb(255, 255, 255);\" name=\"ChDivSummary\">光微流激光是近些年兴起的一种新型集成技术。它包含了微流通道,光学微腔和激光增益介质,在生化传感方面有着显著优势。本文实现的光微流激光免疫比浊法不仅保留了传统免疫比浊法操作简单、成本低、检测时间快、均相反应等优势,同时又具有光微流生化传感器检测灵敏度高、检测范围较大、检测样品消耗少、便于集成的优势,在临床诊断,食品安全检测和环境监测等领域有着巨大的潜在应用价值。本文主要做了以下工作:本文首先通过搭建传统免疫比浊法的实验平台,测得试剂盒反应时间曲线,为后文中采用终点法测量,反应时间定在十分钟提供了实验依据。本文提出基于法珀腔荧光增强型免疫比浊法。通过法珀腔增强荧光与抗原抗体复合物之间的作用次数,提高检测的灵敏度。实验中,采用两片反射率分别为99.5%和90.5%的反射镜构成法珀腔。将罗丹明B水溶液与抗原抗体复合物悬浮液混合均匀,在同一泵浦光下测得不同抗原浓度对应的光谱,将光谱强度积分后可得到抗原浓度与荧光强度之间的关系。通过这种方法测得的IgG的浓度范围为1.79×10<sup style=\"margin: 0px; padding: 0px;\">-4</sup>g/L到1.79×10<sup style=\"margin: 0px; padding: 0px;\">-1</sup>g/L。在法珀腔荧光增强型免疫比浊法的基础上,提出了光微流激光免疫比浊法。通过提高泵浦光能量,调整泵浦光入射位置,并优化罗丹明B水溶液的浓度,将法珀腔产生的激光波长调整到600nm左右。再将抗原抗体反应形成的复合物悬浮液与罗丹明B水溶液混合均匀,通入微流通道中。由于复合物加入到谐振腔内,增加了谐振腔内的损耗系数,由于激光的放大作用,这种细微的变化对激光的强度有着巨大的影响。通过这种方法测得的IgG浓度范围为1.79×10<sup style=\"margin: 0px; padding: 0px;\">-10</sup>g/L到1.79×10<sup style=\"margin: 0px; padding: 0px;\">-5</sup>g/L。&nbsp;</span></p>','2019-11-16 16:24:40',19,0,20),(12,'浅谈怎样做好PPT教学课件','多媒体 教学课件 制作原则 ','随着计算机的普及和多媒体技术的发展,运用多媒体上课已逐渐成为一种趋势。因而制作教学课件也成为任课教师的一项基本功。教学课件制作的质量会直接影响到教学质量,本文通过讲述PPT教学课件制作的基本原则,来谈一下怎样做好PPT教学课件。 ','<p><span style=\"text-align: justify; color: rgb(51, 51, 51); font-family: &quot;Microsoft Yahei&quot;; background-color: rgb(255, 255, 255);\">随着计算机的普及和多媒体技术的发展,运用多媒体上课已逐渐成为一种趋势。因而制作教学课件也成为任课教师的一项基本功。教学课件制作的质量会直接影响到教学质量,本文通过讲述PPT教学课件制作的基本原则,来谈一下怎样做好PPT教学课件。&nbsp;</span></p>','2019-11-16 16:49:20',5,0,22),(18,'电子商务中计算机网络安全及措施','电子商务 计算机网络  安全问题 对策 ','随着科技网络时代的到来,当前电子商务在社会中的发展情况非常可观。但是电子商务计算机网络在运行的过程中有关的网络安全问题却层出不穷,对电子商务的发展而言已经带来了很大的不利影响。为此,本文通过对现阶段电子商务中主要存在的三大计算机网络安全问题进行简要阐述的基础上,然后对有关能够提高计算机网络安全的对策进行深入的探讨。 ','<p>内容<br/></p>','2019-11-16 21:06:58',7,0,26),(20,'基于新型有机染料的光微流激光及其爆炸物浓度传感研究','光微流  染料激光  法布里-珀罗谐振腔  新型有机染料  爆炸物检测 ','光微流激光器作为光和微流控技术的结合体,集成了发光增益材料、光学谐振器和微流通道,在生化传感方面具有显著优势。将新型有机染料应用于光微流激光,染料分子结构和浓度的微小变化在微腔的放大作用下会对出射激光产生非常大的影响,与传统的荧光传感相比具有明显的优势。','<p><span style=\"text-align: justify; color: rgb(51, 51, 51); text-transform: none; text-indent: 0px; letter-spacing: normal; font-family: &quot;Microsoft Yahei&quot;; font-size: 16px; font-style: normal; font-weight: 400; word-spacing: 0px; float: none; display: inline !important; white-space: normal; orphans: 2; widows: 2; background-color: rgb(255, 255, 255); font-variant-ligatures: normal; font-variant-caps: normal; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;\">由于光微流激光器中的法布里-珀罗谐振结构,微流激光比荧光检测方法更灵敏,而且激光信号具有更窄的线宽和更好的方向性,收集的传感信号光通过空间滤波、光谱选择等方法可以很好地滤除荧光背景的干扰。最主要的,光微流激光的强度比荧光强度高几个数量级,可实现更大的检测范围。本文实现了基于新型有机染料的光微流激光,一方面保留了光微流激光灵敏度高、探测极限低、检测范围宽、样品用量小、成本低等特点和优势,另一方面荧光有机染料具有快速响应,低成本和易于合成等优点,可用于特定的待测物识别与传感。本文围绕基于新型有机染料的光微流染料激光及其传感特性开展工作,主要研究了新型有机染料的微流激光特性和基于新型有机染料的光微流爆炸物传感及其性能优化。</span></p>','2019-11-20 20:23:13',16,2,20),(21,'光微流微腔干涉传感器研究','光微流环形谐振腔  回音壁模式  流速传感器 氧化石墨烯 ','光微流微腔传感技术是结合光微流技术与光学微腔的一个新兴研究领域,它极大地发挥了两者的优势。一方面利用了光学微腔良好的光学特性,光学微腔具有极高的Q值、体积小、方便系统集成等优点：另一方面又将微流技术的优势发挥出来,能够方便地对流体样品进行操控,并同时进行光学检测。光微流微腔传感技术的发展对许多领域具有巨大的潜在影响,已被广泛用于生物、化学方面的检测分析。光微流微腔传感器在应用方面具有诸多优势,比如：可以实现高分辨率和高灵敏度的探测、使用的样品和试剂量极少、利用流体实现样品和试剂的方便操控、快速反应和分析、分析仪小型化、方便系统集成、复用性强等。','<p><span id=\"ChDivSummary\" style=\"margin: 0px; padding: 0px; text-align: justify; color: rgb(51, 51, 51); text-transform: none; text-indent: 0px; letter-spacing: normal; font-family: &quot;Microsoft Yahei&quot;; font-size: 16px; font-style: normal; font-weight: 400; word-spacing: 0px; white-space: normal; orphans: 2; widows: 2; background-color: rgb(255, 255, 255); font-variant-ligatures: normal; font-variant-caps: normal; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;\" name=\"ChDivSummary\">本文以毛细管环形谐振腔为实验平台,利用其优良的光学特性,围绕光微流微腔干涉传感器开展研究工作,实现了无源的流速传感和有源的氧化石墨烯检测：(1)近些年来,微流技术已经成为一个研究热点,并被广泛应用到生物化学分析中,例如流式细胞仪、药物检测等,在这些应用中,微流的流速扮演着重要的角色。因此,流速的测量以及精确控制都是非常具有意义的。本文提出了一种微流通道中的基于热传递效应的光微流流速传感器,腐蚀的毛细管既作为光微流环形谐振腔,又作为微流通道。通过监测回音壁模式谐振峰的光谱漂移来测量流速,得到的测量范围为2-100 μL/min,最小可探测流速变化量为30 nL/min,探测准确度大约为5.2%。该传感器具有很好的重复性,而且在低流速段具有极高的灵敏度,它在与微流通道结合方面极具优势,特别是毛细管型微流通道。(2)本文提出了一种基于光微流激光的氧化石墨烯检测技术。采用罗丹明6G作为增益介质,薄壁石英毛细管作为微流通道,同时作为微环谐振腔为微流激光提供反馈增强。当罗丹明6G溶液和氧化石墨烯溶液混合时,会发生电荷转移,并生成复合物,导致激光强度明显的下降,甚至完全淬灭,因此利用氧化石墨烯对微流激光的淬灭效应实现其浓度检测。通过选择不同浓度的罗丹明,可以实现氧化石墨烯测量范围的动态变化。实验采用浓度为200 μg/ml的罗丹明,实现了0-300 μg/ml宽范围氧化石墨烯浓度检测,覆盖了氧化石墨烯生物兼容性研究的重点浓度范围,具有应用前景。&nbsp;</span></p>','2019-11-20 20:37:19',67,0,20),(22,'OFFICE办公软件技巧研究','Office  软件  办公自动化  应用','作为日常工作中最常见的办公软件之一,Office强大的功能为人们的工作提供了极大的便捷。Word和Excel是Office的主要子软件,具有文字处理和表格制作等功能。为进一步促进办公自动化程度的提升,本文就Office办公软件中最常用到的两个软件——Word和Excel的使用技巧展开探究,总结实践经验,希望能给业内同行带来一些启迪。 ','<p>信息时代的悄然而致使人们的办公形式发生了天翻地覆的改变, 新技术层出不穷, 互联网和计算机的出现使人与人之间的联系更加紧密, 办公自动化技术日益成熟, 不但提高了办公效率, 同时还提升了工作质量。信息时代的悄然而致使人们的办公形式发生了天翻地覆的改变, 新技术层出不穷, 互联网和计算机的出现使人与人之间的联系更加紧密, 办公自动化技术日益成熟, 不但提高了办公效率, 同时还提升了工作质量。</p>','2019-11-23 14:09:54',4,0,22),(23,'Office办公软件在办公自动化中的实际应用','办公软件 应用  办公自动化 ','本文探讨了办公自动化的含义, 分析了办公软件在办公自动化中的实际应用意义, 研究了Office办公软件中的常用技巧。','<p><span style=\"text-align: justify; color: rgb(51, 51, 51); text-transform: none; text-indent: 32px; letter-spacing: 0.6px; font-family: Arial, 微软雅黑, &quot;Microsoft YaHei&quot;, serif; font-size: 16px; font-style: normal; font-weight: 400; word-spacing: 0px; float: none; display: inline !important; white-space: normal; orphans: 2; widows: 2; background-color: rgb(255, 255, 255); font-variant-ligatures: normal; font-variant-caps: normal; -webkit-text-stroke-width: 0px; text-decoration-style: initial; text-decoration-color: initial;\">办公自动化是在日常工作中, 使用互联网, 本着简化工作流程的概念, 以电脑为核心, 使用各种实用工作设备和最新通信手段的办公方式。它可以快速全面地收集、存储、处理和使用信息, 使相关人员迅速便捷地共享信息, 高效合作。办公自动化一反过去繁杂、冗长的纯个人工作习惯, 服务于复杂管理与重点决策, 最终提升工作效率, 改善工作质量。办公软件一般是指办公工作中的常用软件, 主要功能包括文字编辑、表格制作、PPT制作、数据库搭建与管理等。目前, 市场上的主流办公软件是Office、WPS、2000Red Office、CTOP协同的OA等系列软件, 这些软件都是由相关公司研发并提供, 成为电脑用户中广泛使用的办公软件, 在数据统计、会议记录、会议展示、现代化办公等领域普遍使用, 是办公领域使用范围最广的一些办公软件</span></p>','2019-11-23 14:10:40',8,1,22);

/*Table structure for table `t_blogger` */

DROP TABLE IF EXISTS `t_blogger`;

CREATE TABLE `t_blogger` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `proFile` text COMMENT '个人简介',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `sign` varchar(128) DEFAULT NULL COMMENT '个性签名',
  `imageName` varchar(128) DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='博主表';

/*Data for the table `t_blogger` */

insert  into `t_blogger`(`id`,`username`,`password`,`proFile`,`nickname`,`sign`,`imageName`) values (1,'宋江','ba61ce8fa1e3725876e6363c76043c8d','<p>梁山大哥</p>','及时雨','梁山好汉','20191119094211.jpg'),(2,'卢俊义','ba61ce8fa1e3725876e6363c76043c8d','<p>梁山第二把交椅，北京大名府<img src=\"/Blog/static/userImages/20191117/1573984499947084048.jpg\" title=\"1573984499947084048.jpg\" alt=\"5a6153570b366.jpg\"/></p>','玉麒麟','梁山好汉','20191117055502.jpg'),(3,'吴用','ba61ce8fa1e3725876e6363c76043c8d',NULL,'智多星',NULL,NULL),(4,'公孙胜','ba61ce8fa1e3725876e6363c76043c8d',NULL,'入云龙',NULL,NULL);

/*Table structure for table `t_blogtype` */

DROP TABLE IF EXISTS `t_blogtype`;

CREATE TABLE `t_blogtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `typeName` varchar(128) DEFAULT NULL COMMENT '类型名称',
  `orderNo` int(11) DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='博客类型表';

/*Data for the table `t_blogtype` */

insert  into `t_blogtype`(`id`,`typeName`,`orderNo`) values (1,'Java',1),(5,'数据结构与算法',43),(13,'SpringBoot',7),(16,'设计模式',10),(18,'软件工程',13),(20,'光微流激光',4),(21,'操作系统',6),(22,'办公软件',20),(23,'Spring',3),(25,'数据库原理',2),(26,'计算机网络',5);

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userIp` varchar(64) DEFAULT NULL COMMENT '评论者ip地址',
  `blogId` int(11) DEFAULT NULL COMMENT '被评论博客的主键',
  `content` varchar(1024) DEFAULT NULL COMMENT '评论内容',
  `commentDate` datetime DEFAULT NULL COMMENT '评论时间',
  `state` int(11) DEFAULT NULL COMMENT '评论状态：0未审核，1审核通过，2审核不通过',
  PRIMARY KEY (`id`),
  KEY `blogId` (`blogId`),
  CONSTRAINT `t_comment_ibfk_1` FOREIGN KEY (`blogId`) REFERENCES `t_blog` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='评论表';

/*Data for the table `t_comment` */

insert  into `t_comment`(`id`,`userIp`,`blogId`,`content`,`commentDate`,`state`) values (2,'127.0.0.2',6,'不错','2019-11-22 10:09:58',1),(3,'127.0.0.3',9,'不太好','2019-11-13 10:10:25',2),(4,'127.0.0.3',11,'还行','2019-11-14 10:34:26',1),(5,'127.0.0.4',5,'不好','2019-11-21 10:34:57',1),(6,'127.0.0.4',11,'OK','2019-11-22 21:11:55',1),(7,'127.0.0.4',11,'OK','2019-11-13 21:11:58',1),(8,'127.0.0.4',11,'OK','2019-11-22 21:12:10',1),(9,'127.0.0.4',11,'水平太低了','2019-11-13 21:12:23',1),(10,'127.0.0.4',11,'还可以','2019-11-22 21:12:19',1),(11,'127.0.0.4',11,'还可以','2019-11-20 21:12:26',1),(12,'127.0.0.4',11,'还可以','2019-11-28 21:12:29',1),(13,'127.0.0.4',11,'还可以','2019-11-22 21:12:35',1),(14,'127.0.0.4',11,'还可以','2019-11-22 21:12:32',1),(15,'127.0.0.4',11,'还可以','2019-11-06 21:12:38',1),(16,'127.0.0.4',11,'很强','2019-11-04 21:15:11',1),(17,'127.0.0.4',11,'很强','2019-11-05 21:15:15',1),(18,'127.0.0.4',11,'很强','2019-11-05 21:15:18',1),(19,'127.0.0.4',11,'很强','2019-11-05 21:15:22',1),(20,'127.0.0.4',11,'很强','2019-11-19 21:15:25',1),(21,'0:0:0:0:0:0:0:1',20,'好的','2019-11-23 10:07:47',1),(22,'0:0:0:0:0:0:0:1',20,'差不多','2019-11-23 10:09:31',2),(23,'0:0:0:0:0:0:0:1',10,'还行','2019-11-23 12:31:47',1),(24,'0:0:0:0:0:0:0:1',23,'很强','2019-11-29 16:29:24',1);

/*Table structure for table `t_link` */

DROP TABLE IF EXISTS `t_link`;

CREATE TABLE `t_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `linkName` varchar(128) DEFAULT NULL COMMENT '网站名称',
  `linkUrl` varchar(512) DEFAULT NULL COMMENT '链接网站地址',
  `orderNo` int(11) DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_link` */

insert  into `t_link`(`id`,`linkName`,`linkUrl`,`orderNo`) values (1,'百度','http://www.baidu.com',1),(2,'电子科技大学','https://www.uestc.edu.cn/',2),(5,'四川大学','http://www.scu.edu.cn/',3),(6,'中国知网','https://www.cnki.net/',4),(7,'bilibili','https://www.bilibili.com/',5);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
