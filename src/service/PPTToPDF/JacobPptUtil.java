package service.PPTToPDF;

import java.io.File;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class JacobPptUtil {

	public static final int WORD_HTML = 8;
	public static final int WORD_TXT = 7;
	public static final int EXCEL_HTML = 44;
	public static final int ppSaveAsPDF = 32;
	public static final int ppSaveAsJPG = 17;
	
	private static final String ADD_CHART = "AddChart";
	private ActiveXComponent ppt;
	ActiveXComponent presentations;
	private ActiveXComponent presentation;
	
	/**
	 * 构造一个新的PPT
	 * @param isVisble
	 */
	
	public JacobPptUtil(boolean isVisble){
		if(null == ppt){
			ppt = new ActiveXComponent("PowerPoint.Application");
			ppt.setProperty("Visible", new Variant(isVisble));
			presentations = ppt.getPropertyAsComponent("Presentations");
			presentation =presentations.invokeGetComponent("Add", new Variant(1));
		}
	}
	
	public JacobPptUtil(String filePath,boolean isVisble)throws Exception{
		if (null == filePath || "".equals(filePath)) {
			throw new Exception("文件路径为空!");
		}
		File file = new File(filePath);
		if (!file.exists()) {
			throw new Exception("文件不存在!");
		}
		ppt = new ActiveXComponent("PowerPoint.Application");
		setIsVisble(ppt, isVisble);
		// 打开一个现有的 Presentation 对象
		ActiveXComponent presentations = ppt.getPropertyAsComponent("Presentations");
		presentation = presentations.invokeGetComponent("Open", new Variant(filePath), new Variant(true));
	}
	
	public static void ppt2pdf(String source, String target,boolean isVisible) {
		System.out.println("启动PPT");
		long start = System.currentTimeMillis();
		ActiveXComponent app = null;
		try {
			app = new ActiveXComponent("Powerpoint.Application");
			Dispatch presentations = app.getProperty("Presentations")
					.toDispatch();
			System.out.println("打开文档" + source);
			Dispatch presentation = Dispatch.call(presentations,//
					"Open", source,// FileName
					true,// ReadOnly
					true,// Untitled 指定文件是否有标题。
					isVisible // WithWindow 指定文件是否可见。
					).toDispatch();

			System.out.println("转换文档到PDF " + target);
			File tofile = new File(target);
			if (tofile.exists()) {
				tofile.delete();
			}
			Dispatch.call(presentation,//
					"SaveAs", //
					target, // FileName
					ppSaveAsPDF);

			Dispatch.call(presentation, "Close");
			long end = System.currentTimeMillis();
			System.out.println("转换完成..用时：" + (end - start) + "ms.");
		} catch (Exception e) {
			System.out.println("========Error:文档转换失败：" + e.getMessage());
		} finally {
			if (app != null)
				app.invoke("Quit");
		}
	}
	
	public static void ppt2img(String source, String target,boolean isVisible) {
		System.out.println("启动PPT");
		long start = System.currentTimeMillis();
		ActiveXComponent app = null;
		try {
			app = new ActiveXComponent("Powerpoint.Application");
			Dispatch presentations = app.getProperty("Presentations")
					.toDispatch();
			System.out.println("打开文档" + source);
			Dispatch presentation = Dispatch.call(presentations,//
					"Open", source,// FileName
					true,// ReadOnly
					true,// Untitled 指定文件是否有标题。
					isVisible // WithWindow 指定文件是否可见。
					).toDispatch();

			System.out.println("转换文档到JPG " + target);
			
			Dispatch.call(presentation,//
					"SaveAs", //
					target, // Foldername
					ppSaveAsJPG);

			Dispatch.call(presentation, "Close");
			long end = System.currentTimeMillis();
			System.out.println("转换完成..用时：" + (end - start) + "ms.");
		} catch (Exception e) {
			System.out.println("========Error:文档转换失败：" + e.getMessage());
		} finally {
			if (app != null)
				app.invoke("Quit");
		}
	}
	
	/**
	 * 将ppt转化为图片
	 * 
	 * @param pptfile
	 * @param saveToFolder
	 * @author liwx
	 */
	public void PPTToJPG(String saveToFolder) {
		try {
			saveAs(presentation, saveToFolder, ppSaveAsJPG);
			if (presentation != null) {
				Dispatch.call(presentation, "Close");
			}
		} catch (Exception e) {
			ComThread.Release();
		} finally {
			if (presentation != null) {
				//System.out.println("这里可能存在问题，但是经检验并没有出现问题");
				//Dispatch.call(presentation, "Close");
			}
			ppt.invoke("Quit", new Variant[] {});
			ComThread.Release();
		}
	}
	
	public void PPTToPDF(String saveFile) {
		try {
			saveAs(presentation, saveFile, ppSaveAsPDF);
			if (presentation != null) {
				Dispatch.call(presentation, "Close");
			}
		} catch (Exception e) {
			ComThread.Release();
		} finally {
			if (presentation != null) {
				//System.out.println("这里可能存在问题，但是经检验并没有出现问题");
				//Dispatch.call(presentation, "Close");
			}
			ppt.invoke("Quit", new Variant[] {});
			ComThread.Release();
		}
	}
	/**
	 * 播放ppt
	 * 
	 * @param pptFile
	 * @date 2009-7-4
	 * @author YHY
	 */
	public void PPTShow(String pptFile) {
		// powerpoint幻灯展示设置对象
		ActiveXComponent setting = presentation
				.getPropertyAsComponent("SlideShowSettings");
		// 调用该对象的run函数实现全屏播放
		setting.invoke("Run");
		// 释放控制线程
		ComThread.Release();
	}

	/**
	 * ppt另存为
	 * 
	 * @param presentation
	 * @param saveTo
	 * @param ppSaveAsFileType
	 * @date 2009-7-4
	 * @author YHY
	 */
	public void saveAs(Dispatch presentation, String saveTo,
			int ppSaveAsFileType)throws Exception {
		Dispatch.call(presentation, "SaveAs", saveTo, new Variant(
				ppSaveAsFileType));
	}
	/**
	 * 关闭PPT并释放线程
	 * @throws Exception
	 */
	public void closePpt()throws Exception{
		if (null != presentation) {
			Dispatch.call(presentation, "Close");
		}
		ppt.invoke("Quit", new Variant[]{});
		ComThread.Release();
	}
	/**
	 * 运行PPT
	 * @throws Exception
	 */
	public void runPpt()throws Exception{
		ActiveXComponent setting = presentation.getPropertyAsComponent("SlideShowSettings");
		setting.invoke("Run"); 
	}
	/**
	 * 设置是否可见
	 * @param visble
	 * @param obj
	 */
	private void setIsVisble(Dispatch obj,boolean visble)throws Exception{
		Dispatch.put(obj, "Visible", new Variant(visble)); 
	}
	/**
	 * 在幻灯片对象上添加新的幻灯片
	 * @param slides
	 * @param pptPage 幻灯片编号
	 * @param type 4:标题+表格	2:标题+文本	3:标题+左右对比文本	5:标题+左文本右图表	6:标题+左图表右文本	7:标题+SmartArt图形	8:标题+图表
	 * @return
	 * @throws Exception
	 */
	private Variant addPptPage(ActiveXComponent slides,int pptPage,int type)throws Exception{
		return slides.invoke("Add", new Variant(pptPage), new Variant(type));
	}
	
	/**
	 * 
	 * @param pageShapes 页面的SHAPES的对象
	 * @param chartType 图表类型
	 * @param leftDistance 距离左边框的距离
	 * @param topDistance 距离上边框的距离
	 * @param width 图表的宽度
	 * @param height 图表的高度
	 * @return
	 * @throws Exception
	 */
	public Dispatch addChart(Dispatch pageShapes,int chartType,int leftDistance,int topDistance,int width,int height)throws Exception{
		Variant chart = Dispatch.invoke(pageShapes, ADD_CHART, 1, new Object[]{
				new Integer(chartType),//图表类型
				new Integer(leftDistance),//距离左边框的距离
				new Integer(topDistance),//距离上边框的距离
				new Integer(width),//图表的宽度
				new Integer(height),//图表的高度
				},
				new int[1]);//错误类型
		
		return chart.toDispatch();
	}
	/**
	 * 获取第几个幻灯片
	 * @param index 序号，从1开始
	 * @return
	 * @throws Exception
	 */
	public Dispatch getPptPage(int pageIndex)throws Exception{
		//获取幻灯片对象
		ActiveXComponent slides = presentation.getPropertyAsComponent("Slides");
		//获得第几个PPT
		Dispatch pptPage = Dispatch.call(slides, "Item", new Object[]{new Variant(pageIndex)}).toDispatch();
		Dispatch.call(pptPage, "Select");
		return pptPage;
	}
	/**
	 * 添加表格
	 * @param pageShapes 页面对象
	 * @param rows 行数
	 * @param columns 列数
	 * @param leftDistance 距离左边距离
	 * @param topDistance 距离顶部距离
	 * @param width 宽度
	 * @param height 高度
	 * @return
	 * @throws Exception
	 */
	public Dispatch addTable(Dispatch pageShapes, long rows,
			long columns, int leftDistance, int topDistance, int width,
			int height) throws Exception {
		return Dispatch.invoke(
				pageShapes,
				"AddTable",
				1,
				new Object[] { new Long(rows), new Long(columns),
						new Integer(leftDistance), new Integer(topDistance),
						new Integer(width), new Integer(height) }, new int[1])
				.toDispatch();
	}
	/**
	 * 在Selection对象上修改TEXT对象的值
	 * @param selectionObj
	 * @param value
	 * @throws Exception
	 */
	public void addTextValue(Dispatch selectionObj,String value)throws Exception{
		Dispatch shapeRange=(Dispatch)Dispatch.get(selectionObj, "ShapeRange").getDispatch();
		Dispatch textFrame=(Dispatch)Dispatch.get(shapeRange, "TextFrame").getDispatch(); 
		Dispatch textRange=(Dispatch)Dispatch.get(textFrame, "TextRange").getDispatch(); 
		Dispatch.call(textRange, "Select");
		Dispatch.put(textRange,"Text",value);
	}
	/**
	 * 将数据添加到制定的单元格内
	 * @param cell 单元格对象
	 * @param value 需要添加的数据
	 * @throws Exception
	 */
	public void addCellValue(Dispatch cell,Object value)throws Exception{
		Dispatch cellShape = Dispatch.get(cell, "Shape").toDispatch();
		Dispatch cellFrame = Dispatch.get(cellShape, "TextFrame").toDispatch();
		Dispatch cellRange = Dispatch.get(cellFrame, "TextRange").toDispatch();
		Dispatch.put(cellRange, "Text", value);
	}
	/**
	 * 合并单元格,合并之后原来两个单元格的内容将放到一个单元格里面
	 * 如果开始单元格和结束单元之间跨几个单元格，将会一起被合并
	 * @param cell 开始单元格
	 * @param cell2 结束单元格
	 * @return
	 * @throws Exception
	 */
	public void mergeCell(Dispatch cell,Dispatch cell2)throws Exception{
		Dispatch.invoke(cell, "Merge", 1, new Object[]{cell2}, new int[1]);
	}
	/**
	 * 获取表格的制定单元格
	 * @param tableObj 表格对象
	 * @param rowNum 第几行，从1开始
	 * @param columnRum 第几列，从1开始
	 * @return
	 * @throws Exception
	 */
	public Dispatch getCellOfTable(Dispatch tableObj,int rowNum,int columnRum)throws Exception{
		return Dispatch.invoke(tableObj, "Cell", Dispatch.Method, new Object[]{new Long(rowNum),new Long(columnRum)}, new int[1]).toDispatch();
	}
/**
	 * 设置单元格背景颜色
	 * @param cellObj
	 * @param colorIndex
	 * @throws Exception
	 */
	public void setCellBackColor(Dispatch cellObj,int colorIndex)throws Exception{
		Dispatch cellShape = Dispatch.get(cellObj, "Shape").toDispatch();
		Dispatch fillObj = Dispatch.get(cellShape, "Fill").toDispatch();
		Dispatch backColor = Dispatch.get(fillObj, "ForeColor").toDispatch();
		Dispatch.put(backColor, "ObjectThemeColor", colorIndex);
		Dispatch.put(fillObj, "ForeColor", backColor);
	}
	/**
	 * 修改表格的样式，默认样式为:{5C22544A-7EE6-4342-B048-85BDC9FD1C3A}
	 * @param tableObj 表格对象
	 * @param styleId 样式ID
	 * @throws Exception
	 */
	public void editTableSyle(Dispatch tableObj,String styleId)throws Exception{
		if(null == tableObj){
			throw new Exception("无效的表格对象!");
		}
		if(null == styleId || "".equals(styleId)){
			throw new Exception("无效的样式ID!");
		}
		Dispatch.invoke(tableObj, "ApplyStyle", Dispatch.Method, new Object[]{styleId}, new int[1]);
	}
	/**
	 * 在TABLE对象上添加列
	 * @param tableObj
	 * @param beforeColumn
	 * @throws Exception
	 */
	public void addTableColumn(Dispatch tableObj,int beforeColumn)throws Exception{
		Dispatch columns = (Dispatch) Dispatch.get(tableObj, "Columns").getDispatch();
		int count = Dispatch.get(columns, "Count").getInt();
		if(beforeColumn > count || beforeColumn < 1){
			throw new Exception("无效的列索引!");
		}
		Dispatch.invoke(columns, "Add", Dispatch.Method, new Object[]{beforeColumn}, new int[1]);
	}
	/**
	 * 在TABLE对象上添加行
	 * @param tableObj
	 * @param beforeColumn
	 * @throws Exception
	 */
	public void addTableRow(Dispatch tableObj,int beforeRow)throws Exception{
		Dispatch rows = (Dispatch) Dispatch.get(tableObj, "Rows").getDispatch();
		int count = Dispatch.get(rows, "Count").getInt();
		if(beforeRow > count || beforeRow <1){
			throw new Exception("无效的行索引!");
		}
		Dispatch.invoke(rows, "Add", Dispatch.Method, new Object[]{beforeRow}, new int[1]);
	}
	/**
	 * 修改单个些列的图表类型
	 * @param chartObj 图表对象
	 * @param seriIndex 系列索引，从1开始
	 * @param chartType 图表类型
	 * @throws Exception
	 */
	public void updateSeriChartType(Dispatch chartObj,int seriIndex,int chartType)throws Exception{
		Dispatch Seri1 = Dispatch.call(chartObj, "SeriesCollection", new Variant(seriIndex)).toDispatch(); 	
        Dispatch.put(Seri1,"ChartType",chartType);
	}
	/**
	 * 设置是否显示图表的数据表格,当新增一个表格时默认时不显示的
	 * @param chartObj 表格对象
	 * @param bValue 布尔值，ture为显示，false为不显示
	 * @throws Exception
	 */
	public void setIsDispDataTable(Dispatch chartObj,boolean bValue)throws Exception{
		if(null == chartObj){
			throw new Exception("无效的图表对象!");
		}
		Dispatch.put(chartObj, "HasDataTable", bValue);
	}
	/**
	 * 获取表格的样式ID
	 * @param tableObj
	 * @return
	 * @throws Exception
	 */
	public String getTableStyleId(Dispatch tableObj)throws Exception{
		Dispatch tableStyle = Dispatch.get(tableObj, "Style").toDispatch();
		return Dispatch.get(tableStyle, "Id").toString();
	}
	/**
	 * 设置图表上是否显示数据表格
	 * @param chartObj
	 * @param value
	 * @throws Exception
	 */
	public void setHasDataTable(Dispatch chartObj,boolean value)throws Exception{
		Dispatch.put(chartObj, "HasDataTable", value);
	}
	
	public void getGeneragePpt() throws Exception {
		// 生成一个新的ppt 对象 
		Dispatch windows = presentation.getProperty("Windows").toDispatch();
		Dispatch window = Dispatch.call(windows, "Item", new Variant(1)).toDispatch();
		Dispatch selection = Dispatch.get(window, "Selection").toDispatch();
		//获取幻灯片对象
		ActiveXComponent slides = presentation.getPropertyAsComponent("Slides");

		//添加第一张幻灯片； 标题+副标题
		addPptPage(slides, 1, 1);
		
		Dispatch slideRange=(Dispatch)Dispatch.get(selection, "SlideRange").getDispatch();
		Dispatch shapes=(Dispatch)Dispatch.get(slideRange, "Shapes").getDispatch();
		//获取幻灯片中的第一个元素
		Dispatch shape1 = Dispatch.call(shapes, "Item", new Variant(1)).toDispatch();
		//获取幻灯片中的第二个元素
		Dispatch shape2 = Dispatch.call(shapes, "Item", new Variant(2)).toDispatch();
		//选中第一个元素
		Dispatch.call(shape1, "Select");
		//添加值
		addTextValue(selection, "测试主标题");
		//操作PPT一页中的第二个shape
		Dispatch.call(shape2, "Select");
		addTextValue(selection, "测试副标题");
		//添加第二张幻灯片(标题+图表)
		Variant v = addPptPage(slides, 2, 8);
		//获取第二个PPT对象
		Dispatch pptTwo = (Dispatch) v.getDispatch();
		//激活当前PPT对象
		Dispatch.call(pptTwo, "Select");
		//获取PPT中的shapes
		shapes = Dispatch.get(pptTwo, "Shapes").toDispatch();
		Dispatch shapeText = Dispatch.call(shapes, "Item", new Variant(1)).toDispatch();
		//操作标题
		Dispatch.call(shapeText, "Select");
		addTextValue(selection, "测试图表标题");
		//添加图表
		
		Dispatch chartDisp = addChart(shapes, 2, 10, 130, 700, 200);
		Dispatch chartObj=(Dispatch) Dispatch.get(chartDisp, "Chart").getDispatch();
		
		setHasDataTable(chartObj, true);
		
		Dispatch chartData=Dispatch.get(chartObj, "ChartData").toDispatch();
		
		//Variant chartStyleV=Dispatch.get(chartObj, "ChartFont");
		
		Dispatch workBook=(Dispatch) Dispatch.get(chartData, "Workbook").getDispatch();
		
		Dispatch workSheets=(Dispatch) Dispatch.get(workBook, "Worksheets").getDispatch();
		
		Dispatch workSheetItem = Dispatch.call(workSheets, "Item", new Variant(1)).toDispatch();
		
		Dispatch cell = Dispatch.invoke(workSheetItem, "Range", Dispatch.Get,   
                new Object[] { "B3" }, new int[1]).toDispatch();   
        Dispatch.put(cell, "Value", 12);
        //7606
        //修改单个系列的图表类型
        //Dispatch Seri1 = Dispatch.call(chartObj, "SeriesCollection", new Variant(3)).toDispatch(); 	
        //Dispatch.put(Seri1,"ChartType",4);
        updateSeriChartType(chartObj, 3, 4);
		Dispatch chartArea = Dispatch.get(chartObj, "ChartArea").toDispatch();
		
		
		//表格
		/*Dispatch tableDisp = addTable(shapes, 3, 4, 50, 130, 600, 300);
		Dispatch tableObj=Dispatch.get(tableDisp, "Table").getDispatch();
		
		//修改表格样式
		Dispatch.invoke(tableObj, "ApplyStyle", 1, new Object[]{"{5C22544A-7EE6-4342-B048-85BDC9FD1C3A}"}, new int[1]);
		//获取单元格
		Dispatch cell = getCellOfTable(tableObj, 1, 1);
		Dispatch cell2 = getCellOfTable(tableObj, 2, 1);
		addCellValue(cell, "测试着玩的");
		addCellValue(cell2, "哈哈");
		//合并单元格
		//mergeCell(cell, cell2);
		addTableColumn(tableObj, 4);
		addTableRow(tableObj, 3);*/
		// powerpoint幻灯展示设置对象 
		ActiveXComponent setting = presentation.getPropertyAsComponent("SlideShowSettings");
		//setting.invoke("Run"); 

		//保存ppt 
		presentation.invoke("SaveAs", new Variant("d:/a.ppt"));
		// 释放控制线程 
		ComThread.Release(); 
		//closePpt();
	}
	/**
	 * 解析现有的PPT
	 * @param filePath
	 * @throws Exception
	 */
	public void invokePPTTemplate(String filePath)throws Exception{
		Dispatch windows = presentation.getProperty("Windows").toDispatch();
		Dispatch window = Dispatch.call(windows, "Item", new Variant(1)).toDispatch();
		//获得第几个PPT
		Dispatch pptPage = getPptPage(1);
		Dispatch selection = Dispatch.get(window, "Selection").toDispatch();
		Dispatch slideRange=(Dispatch) Dispatch.get(selection, "SlideRange").getDispatch();
		Dispatch shapes=(Dispatch) Dispatch.get(slideRange, "Shapes").getDispatch();
		//获取幻灯片中的第N个元素
		Dispatch tableDisp = Dispatch.call(shapes, "Item", new Variant(2)).toDispatch();
		//转换为Table对象
		Dispatch tableObj = Dispatch.get(tableDisp, "Table").toDispatch();
		//获得对象的Style的属性
		Dispatch tableStyle = Dispatch.get(tableObj, "Style").toDispatch();
		System.out.println(Dispatch.get(tableStyle, "Id"));
		//{91EBBBCC-DAD2-459C-BE2E-F6DE35CF9A28}
		/*//选中第一个元素
		Dispatch.call(shape1, "Select");
		Dispatch chartObj=Dispatch.get(shape1, "Chart").getDispatch();
		
		Dispatch chartData=Dispatch.get(chartObj, "ChartData").toDispatch();
		
		Dispatch workBook=Dispatch.get(chartData, "Workbook").getDispatch();
		
		Dispatch workSheets=Dispatch.get(workBook, "Worksheets").getDispatch();
		
		Dispatch workSheetItem = Dispatch.call(workSheets, "Item", new Variant(1)).toDispatch(); 		 
		
		Dispatch cell = Dispatch.invoke(shape1, "Range", Dispatch.Get,   
                new Object[] { "B3" }, new int[1]).toDispatch();  */ 
        //修改单个系列的图表类型
        //Dispatch Seri1 = Dispatch.call(chartObj, "SeriesCollection", new Variant(1)).toDispatch(); 	
        //Dispatch.put(Seri1,"ChartType","4");
		
		//Dispatch chartArea = Dispatch.get(chartObj, "ChartArea").toDispatch();
		//清楚图表内容
		//Dispatch.call(chartArea, "ClearContents");
		
		// powerpoint幻灯展示设置对象 
		ActiveXComponent setting = presentation.getPropertyAsComponent("SlideShowSettings");
		//setting.invoke("Run");
		//保存ppt 
		//presentation.invoke("SaveAs", new Variant(filePath));
		// 释放控制线程 
		ComThread.Release(); 
	}
	
	public static void main(String[] strs)throws Exception{
		String filePath="C:/2.ppt";
		String folderPath = "C:/Users/Rail/Desktop/ppt";
		JacobPptUtil jac = new JacobPptUtil(filePath,true);
		jac.PPTToJPG(folderPath);
	}

}

