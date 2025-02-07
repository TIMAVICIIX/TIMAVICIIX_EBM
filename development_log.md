<p style="text-align: center;font-weight: bold;color: lightskyblue;"> TIMAVICIIX's Easy Book Management </p>

### Hi! I'm Robo.Timaviciix📺.
#### &emsp;This document captures some of my thoughts on the development process and my vision for the future of development📜. Of course, you can read it as a diary📝.<br>&emsp;It's very dramatic that I was introduced to module development for _Minecraft_🎮 during the most important days of my software development career, and this is the first time I've developed a module for the game, so I hope you enjoy it.

<p style="text-align: end"> 2025.01.05 <br> Robo.TIMAVICIIX </p>

### 嗨! 我是 Robo.Timaviciix📺.
#### &emsp;这篇文档记录了我在开发过程中的一些思路以及对开发前景的设想📜。当然你可以把它当作日记去看📝。<br>&emsp;很戏剧的是，我在软件开发生涯最重要的日子里中接触到了《我的世界》🎮这款游戏的模组开发，这是我第一次开发该游戏的模组，希望大家喜欢。

<p style="text-align: end"> 2025.01.05 <br> Robo.TIMAVICIIX </p>

TIPS:
```html
❗❗❗
以下的日志就只写中文咯！看不懂请用翻译软件〒▽〒
The following log is only written in Chinese! If you don't understand, please use translation software
❗❗❗
```
---
## <br>2025.01.05
##### &emsp;哈哈，mod教程还没看完呢，先梳理一下mod的制作初衷和需要实现的item,block和功能吧！
##### &emsp;原因是我最近在Minecraft中的一个服务器游玩时，看到了有玩家在服务器中写小说，他们依靠Minecraft中的原生item"书与笔“来写小说；写完之后，玩家们将其放在固定场景的战利品箱子中供大家阅读，就像galgame一样。我在享受阅读的同时也在考虑一些问题。<br>&emsp;这些存放小说书本的箱子上面写着 "看完请放回原位" 。意味着这本小说是被作者签名的成书，但是，这本书被作者编写出来后，需要耗费另外一份"书与笔"；虽然书本能偶复印，但是其他玩家也能够复印这本书，并且能够被随意复印；书本放在宝箱中，如果没有提示，可能还不能被其他玩家发现。我认为这就是我开发这款Mod的初衷。也就是让玩家编写的书本能够有版权意识选择的被复印，出版；此外，这些书本能够通过一些方式展示出来，比如被真正的放到书架上，甚至能够自定义书本封面。 <br>&emsp;
##### "我话讲完，谁赞成？谁反对？" \(\) 下面就草拟出一些我认为该Mod需要的Item之类的吧。

### Item
* #### 复印机【Block】【可放置】\[copier\]：能够接收Minecraft原生书籍，包括"书与笔","成书"等书籍。能够耗费少量的书本与墨囊就复印出大量的书籍。能够进行作者对书本的版权复印设置，并隔离掉原生复印机制（如果你想要复印附魔书，可以通过命令开启权限）
* #### 硒鼓\[toner_cartridge\]：复印机的合成材料，对复印机的合成设置合成门槛，当然，不会很高
* #### 复印机体\[copier_body\]：同上，复印机合成材料
* #### XXX-原稿【Block】【可放置】\[xxx_originals\]：就是签章器生成的原稿咯，无论是附魔书，成书还是书与笔，都能进行签章，签章者既是这本书的作者，只有通过签章这本书才能被复印（当然不会这么死板）
* #### XXX-翻印版【Block】【可放置】\[xxx_reproduced\]：复印机通过原稿复印出的产物，也可以叫复印版

#### 先写到这儿，得睡觉了

---

## <br>2025.01.06
#### &emsp;昨天的item还没写完呢，今天继续吧。
### Item
* #### 墨盒\[ink_box\]：嘿！墨盒和硒鼓不是同一个东西吗！不是的，硒鼓包括了打印机构和墨盒，但是墨盒里面的墨是需要添加的哦！通过墨囊或者煤炭木炭制作墨盒，将墨盒嵌入打印机就行咯？嘶~，这些东西能不能直接放进去啊
* #### 书壳\[book_case\]：什么玩意儿这是？书不是有书壳吗？哦哦，这是用来自定义书本封面的（大悟）
* #### 装订机\[binding_machine\]：书本通过装订机并添加书壳来自定义封面，当封面自定义完成后，复印版也会继承该封面哦

### Block
* #### 签章台【Block】【可放置】\[signature_table\]：能够接收一本书，或者书与笔，并用另一本书进行签章，并合成原稿，该类书本才能够被复印机复印
* #### 便携复印机【Block】【可放置】\[copier\]：能够接收Minecraft原生书籍，包括"书与笔","成书"等书籍。能够耗费少量的书本与墨囊就复印出大量的书籍。能够进行作者对书本的版权复印设置，并隔离掉原生复印机制（如果你想要复印附魔书，可以通过命令开启权限）
* #### 各种木制书架【Block】【可放置】\[t_bookshelf\]：装订封面就是要给人看啊，通过放置书本来展示你的封面吧，书脊会显示你的书名哦
* #### XXX-原稿【Block】【可放置】\[xxx_originals\]：通过放置在书架上,也可以单独堆叠，实际上应该叫书本存储堆
* #### XXX-翻印版【Block】【可放置】\[xxx_reproduced\]：同上


> 我在考虑要不要加入一个新的工作台，这样可以提高配方兼容性

#### 好吧让我们继续，看来我们需要一个独立工作台
* #### 图书管理员的书桌【Block】【可放置】 \[librarian's_desk\]：ChatGPT给它取的名字，还不错哈

#### <br>差不多就是这些吧，开发教程大致看完了，用Kotlin写，效率高一点，画一下材质然后休息啦

---

## <br>2025.01.07
#### &emsp;今天在绘画item图标时又想到了一些Block和item制作流程

### Item
* #### 各类纹理书壳\[bookcase\]：在装订器为你的爱书套上纹理书壳！
* #### 鸡毛掸子\[feather_duster\]：有灰（

### Block
* #### 立式复印机\[duplicator\]：效率是便携复印机的一坤倍
* #### 图书馆大门\[library_gate\]：3x2的大门，辉煌时刻从装修大门开始

#### 今天的item绘画进行顺利，建模教程也看完了，明天继续！

![书本重绘预览](display_files/20250107dispaly.png)

---

## <br>2025.01.08
#### &emsp;又想到了一些item和block

### Block
* #### 独立书本展示台\[book_display\]：单独放一本书进去，还能把书页打开
* #### 玻璃独立书本展示台\[glass_book_display\]：奇异博士都偷不了
* #### 欧式陶瓷茶具套装\[ceramic_tea_set\]：倚着柱子午睡的少女
* #### 小圆桌\[round_table\]：看书必备
* #### 读书椅\[reading_chair\]：没有颈椎负担!

### Item
* #### 蓝色猫猫吊坠\[blue_cat_pendant\]：【增加夜视能力，攻击对手有几率使其迷惑】你在哪儿得到的，别摔坏了！快还回去\(

#### 今天就差不多了吧！又画了一天的图标,建个模睡觉
![0108重绘预览](display_files/20250108display.png)

---

## <br> 2025.01.09
#### &emsp;明明是9号的日志，硬是过了凌晨12点才写
#### &emsp;今天继续新增了一些书的样式，明天再加一个样式就不画了，先把其他方块的图标画出来，建筑方块就不画了，看模型截图要更清晰一点\(不是为了偷懒！\)<br>&emsp;另外便携打印机的建模也出来了，好了睡觉

![0109重绘预览](display_files/20250109display.png)
![便携打印机](display_files/Timaviciix'sPrinter.png)

## <br> 2025.01.10
#### &emsp;距离11号还差 1 h 20 min，今天给立式打印机上色了，又画了几个item，原谅我用ai生成了几个item图标，拿过来改了一下
![0110重绘预览](display_files/20250110display.png)
<img src="display_files/timaviciix's_large_printer.png" style="width:50%;height:50%;">
#### 各类纹理书壳不想画图标了，好累ε(┬┬﹏┬┬)3，先把书本的白模做了，做完碎觉

## <br> 2025.01.11
#### &emsp;哇！书本建模真的好累，我补药画材质啦(T_T)<br>&emsp;等等，给夜之饕宴添加一个书本回收功能，让它放置后能够指定图书管理区域，当书本超过该区域或玩家携带其超过后，书本被收回并归放原位。

### Block
* #### 书梯\[book_stair\]：可以安装在书梯轨道上，能够叠加放置，方便拿取书籍
* #### 书梯轨道\[book_stair_orbit\]：没有这个东西你可能把书梯骑着跑了

#### 好了就这俩了，不画图标了。明天见

## <br> 2025.01.12
#### &emsp;书本白膜还差小型书籍，但是今天又想加入一个书本类型

### Block
* #### 期刊本\[periodical\]：制作简单，印刷方便，适合刊登周期更新的文章

#### 好了好了，今天就先到这儿，明天把小型书籍模型建好，就可以先进行Item开发测试了

## <br>2025.01.13
#### &emsp; 完成了基本书抽象类的编写，整理了项目架构（又重温了一遍编程，还是Java（悲）
#### &emsp;明天继续了，每天也就四五个小时拿来写Mod，太累了

## <br>2025.01.14
#### &emsp;14号居然没写日志？补上补上~

## <br>2025.01.15
#### &emsp;今天在进行Item导入测试，看看图标是否清晰，需不需要重绘。而且还有近半的图标没有绘制，螺旋开发能够避免颠覆性错误 \(￣▽￣\)

## <br>2025.01.16
#### &emsp;错误这不就来了吗，Item注册时发生错误，用Kotlin+Owo库开发风险还是太高了，Kotlin低普适性和Owo太哈人了,,, mad 17号了<br> &emsp;改好了卧槽，17号凌晨2点了

## <br>2025.01.17
#### &emsp;画书壳素材中，重绘了icon，还行吧？明天看看成就系统怎么写！睡觉！！！
![icon](/display_files/icon.png)

## <br>2025.01.18
#### &emsp;今天和自动注册斗智斗勇了一天，Kotlin的AnnotationProcessor有点抽风，以后再解决了，注册就先硬编码了，不然赶不上进度！

## <br>2025.01.19
#### &emsp;好好好，现在我的物品注册流程感觉很畸形，一方面是采用了owo的自动注册框架却要自己注册，另一方面是采用了注解APT却不生效，现在真是进退两难，难道说要交给后人的智慧了吗

## <br>2025.01.20
#### &emsp;现在是20号凌晨12点14分，kapt仍然不能够解决自动注册，我正在尝试使用KSP<br>&emsp;KSP不能解决，正在导入其他素材，就这样了，先把状态机图画出来
![状态机图](display_files/stateDiagram.png)
#### &emsp;目前采用了reified类型推理与KProperty进行实例化注册推断，这是Kotlin在属性反射机制KSP以及KAPT不能使用的情况下的最佳解了：
```kotlin

inline fun <reified T : Item> registrySelf(property: KProperty<*>,): T {

    val itemId = property.name.lowercase()
    val targetItem = T::class.createInstance()

    return Registry.register(Registries.ITEM, Identifier(GlobalData.MOD_ID, itemId), targetItem)

}

val CLASSIC_JOURNAL_BOOK: JournalBook = registrySelf(::CLASSIC_JOURNAL_BOOK)

```
#### 这样就不用显示声明ID了，还简化了类型声明

## <br>2025.01.21
#### &emsp;今天实现了放置方块，并重构了书籍类，将书籍从item中迁移到block中，目前在考虑是否抽象blockEntity到更高级别，将同种类型的书本属性对齐


## <br>2025.01.22
#### &emsp;写这篇日志的时候已经是23号凌晨3点了，22号完成了所有书本模型的导入以及Block的声明<br>&emsp;利用三种BlockEntity类实例化了多种BlockEntityType，这种声明方式有利于统一书本种类，多样化书本纹理

```kotlin
//详见cn.timaviciix.ebm.registers.blocks.BookRegister
val REFINED_LEATHER_BOOK: RefinedLeatherBookBlock = registrySelf(::REFINED_LEATHER_BOOK, 0xff8264, true)

val GENERAL_REFINED_LEATHER_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> =
    FabricBlockEntityTypeBuilder.create({ pos, state ->
        GeneralBookBlockEntity(
            ParamEntityTypes.GENERAL_REFINED_LEATHER_BOOK_TYPE.blockEntityType,
            pos, state
        )
    }, REFINED_LEATHER_BOOK).build()

enum class ParamEntityTypes(val blockEntityType: BlockEntityType<*>) {
    GENERAL_REFINED_LEATHER_BOOK_TYPE(EntityTypes.GENERAL_REFINED_LEATHER_BOOK_TYPE)
}
```

#### 明天...哦不对，今天见！！！

## <br>2025.01.23
#### &emsp;补上23号的日志，23号干了什么来着？哦哦修复了BlockEntity注册问题


## <br>2025.01.24
#### &emsp;导入多个物品模型，正在尝试制作武器

## <br>2025.01.25
#### &emsp;实现了Block动画

## <br>2025.01.26
#### &emsp;正在为各项工作台建模，下一阶段将设置阅读动画并绘制阅读 UI，安好！！

## <br>2025.01.27
#### &emsp;所有工作台建模已完成！目前正在进行彩蛋盔甲开发，设置阅读动画推迟

### Item
#### 罗宾有珠三千问\[Penguin_Armor]\:实际上是企鹅玩偶服

## <br>2025.01.28
#### 彩蛋玩偶服开发完成，下一步进行阅读状态开发，新年快乐！

## <br>2025.01.29
#### 正在开发阅读状态同步机制

## <br>2025.01.30
#### 阅读同步机制艰难开发中，ChatGPT，Coze，DeepSeek哥几个尽给我出馊主意

## <br>2025.01.31
#### 阅读同步机制完成，绘制书本UI中。。。

## <br>2025.02.01
#### 书本UI绘制完成，正在解决UI背景材质与字体等问题

## <br>2025.02.02
#### 今天啥也没做。。。

## <br>2025.02.03
#### 加入了声音事件

## <br>2025.02.04
#### 正在修正LabelComponent存在的Bug

## <br>2025.02.05
#### 字体运行正常，UI界面运行暂时正常，正在实现分段存储与阅读机制

## <br>2025.02.06
#### 将Book中的数据逻辑集中到Data类中处理，并将View与Controller分离，后期将实现MVVM架构

## <br>2025.02.07
#### 完整了工作台UI，目前正在为数据存储与Display的MVVM架构筑底

