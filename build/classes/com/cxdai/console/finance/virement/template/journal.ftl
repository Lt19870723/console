<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:o="urn:schemas-microsoft-com:office:office"
 xmlns:x="urn:schemas-microsoft-com:office:excel"
 xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:html="http://www.w3.org/TR/REC-html40">
 <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <Created>2016-04-07T01:38:10Z</Created>
  <LastSaved>2016-04-07T01:45:20Z</LastSaved>
  <Version>15.00</Version>
 </DocumentProperties>
 <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
  <AllowPNG/>
  <RemovePersonalInformation/>
 </OfficeDocumentSettings>
 <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
  <WindowHeight>8505</WindowHeight>
  <WindowWidth>16155</WindowWidth>
  <WindowTopX>240</WindowTopX>
  <WindowTopY>120</WindowTopY>
  <ProtectStructure>False</ProtectStructure>
  <ProtectWindows>False</ProtectWindows>
 </ExcelWorkbook>
 <Styles>
  <Style ss:ID="Default" ss:Name="Normal">
   <Alignment ss:Vertical="Center"/>
   <Borders/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="11" ss:Color="#000000"/>
   <Interior/>
   <NumberFormat/>
   <Protection/>
  </Style>
  <Style ss:ID="s74">
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="14" ss:Color="#000000"/>
   <Interior ss:Color="#FFFF00" ss:Pattern="Solid"/>
  </Style>
  
  <Style ss:ID="s79">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
  </Style>
  
  <Style ss:ID="s66">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center" ss:WrapText="1"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"/>
   </Borders>
   <NumberFormat ss:Format="000000"/>
  </Style>
 </Styles>
 <Worksheet ss:Name="现金日记账">
  <Table ss:ExpandedColumnCount="6" ss:ExpandedRowCount="${rowSize!""}" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="13.5">
   <Column ss:AutoFitWidth="0" ss:Width="84"/>
   <Column ss:AutoFitWidth="0" ss:Width="123.75" ss:Span="2"/>
   <Column ss:Index="5" ss:AutoFitWidth="0" ss:Width="78.75"/>
   <Column ss:AutoFitWidth="0" ss:Width="264"/>
   <Row ss:Height="18.75">
    <Cell ss:StyleID="s74"><Data ss:Type="String">操作日期</Data></Cell>
    <Cell ss:StyleID="s74"><Data ss:Type="String">借(收入)方</Data></Cell>
    <Cell ss:StyleID="s74"><Data ss:Type="String">贷(支出)方</Data></Cell>
    <Cell ss:StyleID="s74"><Data ss:Type="String">结存</Data></Cell>
    <Cell ss:StyleID="s74"><Data ss:Type="String">借或贷</Data></Cell>
    <Cell ss:StyleID="s74"><Data ss:Type="String">摘要</Data></Cell>
   </Row>
   <#list table as t>
   <Row>
    <Cell ss:StyleID="s79"><Data ss:Type="String">${t.operationDate!""}</Data></Cell>
    <Cell ss:StyleID="s79"><Data ss:Type="String">${t.debitAmount!""}</Data></Cell>
    <Cell ss:StyleID="s79"><Data ss:Type="String">${t.creditAmount!""}</Data></Cell>
    <Cell ss:StyleID="s79"><Data ss:Type="String">${t.balance!""}</Data></Cell>
    <Cell ss:StyleID="s79"><Data ss:Type="String">${t.debitOrCredit!""}</Data></Cell>
    <Cell ss:StyleID="s66"><Data ss:Type="String">${t.remark!""}</Data></Cell>
   </Row>
   </#list>
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.3"/>
    <Footer x:Margin="0.3"/>
    <PageMargins x:Bottom="0.75" x:Left="0.7" x:Right="0.7" x:Top="0.75"/>
   </PageSetup>
   <Print>
    <ValidPrinterInfo/>
    <PaperSizeIndex>9</PaperSizeIndex>
    <HorizontalResolution>600</HorizontalResolution>
    <VerticalResolution>600</VerticalResolution>
   </Print>
   <Selected/>
   <Panes>
    <Pane>
     <Number>3</Number>
     <ActiveRow>6</ActiveRow>
     <ActiveCol>4</ActiveCol>
    </Pane>
   </Panes>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
</Workbook>