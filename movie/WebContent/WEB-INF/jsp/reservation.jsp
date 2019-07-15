<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.List" %>
<%@ page import ="movie.beans.FeeBeans" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約画面</title>
<link rel="stylesheet" href="lib/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/reservation.css">
<script src="lib/vue.min.js"></script>
</head>
<body>
<div class="container-fluid" id="app">
<%
	String[] sheets=new String[100];
	sheets = (String[])request.getAttribute("sheets");
	int sheetCount=0;
	List<FeeBeans> feeList=(List<FeeBeans>)session.getAttribute("feeList");
	List<Integer> reserveSheetList=(List<Integer>)session.getAttribute("reserveSheetList");
%>
	<form action = "MovieReservationController" method = "get" id="reserveForm" >
		<input type="hidden" name="member"value=1>
		<input type="hidden" name="term" value = 1>
		<input type="hidden" name="theater" value=1>
		<input type="hidden" name ="screen" value=1>
		<input type="hidden" name = "fee" v-for="reserveType in reserveSheetType" :value="reserveType">
		<input type="hidden" name = "sheet" v-for="rs in reserveSheetNum" :value="rs">
	</form>
	<article class="row content" v-if="status=='selectSheet'">
		<div class="col-sm-2"></div>
		<div class="col-sm-7">
			<table>
			<%for(int i=0;i<10;i++){ %>
				<tr>
				<%for(int j=(i*10)+1;j<=(i+1)*10;j++){ %>

					<%if(Integer.parseInt(sheets[sheetCount])==j-1){ %>
						<%if(reserveSheetList.indexOf(j)==-1){ %>
						<td>
							<button class="sheet-div active-sheet" @click="selectSheet(<%=j %>)" :class="{'select-sheet':reserveSheetNum.indexOf(<%=j%>)>0}"><%=j %></button>
							<select class="input-ticket" v-if="selectedSheet===<%=j %>" @change="reserve(<%=j%>)" v-model="ticketType">
								<option value=0 disabled>券種を選択してください</option>
								<%for(int idx=0;idx<feeList.size();idx++){ %>
								<option value=<%=idx+1 %>><%=feeList.get(idx).getFeeType() %> ￥<%=feeList.get(idx).getFee() %></option>
								<%} %>
								<option value=-1 :disabled="isDisableCancel">取り消し</option>
							</select>
						</td>
						<%}else{ %>
						<td><button class="sheet-div reserve-sheet" disabled>×</button></td>
						<%} %>
					<%sheetCount++; %>

					<%}else{ %>
					<td>
						<button class="sheet-div disable-sheet" disabled></button>
					</td>
					<%} %>
				<%} %>
				</tr>
			<%} %>
			</table>
			<button type="button" onclick="location.href='top'" class="btn btn-danger d-block">戻る</button>
			<button type="button" @click="submitReserve" class="btn btn-primary d-block" :disabled="cannotReserve">予約する</button>
		</div>
		<div class="col-sm-3">
			<table border="1" class="check-table">
				<tr>
					<th>席番号</th>
					<th>チケットタイプ</th>
					<th>金額</th>
				</tr>
				<%int count=0; %>
				<tr v-for="(sheet,index) in reserveSheet">
					<td>{{sheet['num']}}</td>
					<td>{{getReserveType(sheet['type'])}}</td>
					<td>￥{{getReserveFee(sheet['type'])}}</td>
				</tr>
			</table>
		</div>
	</article>
</div>
</body>
<script>
var app=new Vue({
	el: '#app',
	data:{
		status: 'selectSheet',
		selectedSheet: -1,
		reserveSheet:[],
		sheetNumber: 2,
		ticketType: 0,
		isClicked: false,
		sheets: [],
		isModal: false,
		reserveSheetNum: [],
		reserveSheetType: [],
		isDisableCancel: true,
	},
	methods:{
		selectSheet:function(index){
			this.selectedSheet=index
			this.ticketType=0
			if(this.reserveSheetNum.length>0){
				const sheetNum=this.reserveSheetNum.indexOf(index)
				if(sheetNum>=0){
					this.isDisableCancel=false
				}else{
					this.isDisableCancel=true
				}
			}else{
				this.isDisableCancel=true
			}
		},
		reserve:function(index){
			/* 予約席を1席以上選択しているか? */
			if(this.reserveSheet.length>0){
				//予約席番号のみ抽出
				let tempReserveNum=[]
				for(let i=0;i<this.reserveSheet.length;i++){
					tempReserveNum.push(this.reserveSheet[i]['num'])
				}
				const reserveIndex=tempReserveNum.indexOf(index);
				/* 予約している席を選択したか? */
				if(reserveIndex>=0){
					this.isDisableCancel=false
					console.log(this.isDisableCancel)
					/* チケット種類変更か予約取り消しか? */
					if(this.ticketType==-1){
						this.reserveSheet.splice(reserveIndex,1);
						this.reserveSheetNum.splice(reserveIndex,1);
						this.reserveSheetType.splice(reserveIndex,1);
					}else{
						this.reserveSheet[reserveIndex]['type']=this.ticketType;
						this.reserveSheetType[reserveIndex]=this.ticketType;
					}
					console.log(this.reserveSheetType+" "+this.reserveSheetNum)
				}else{
					//予約したことがない席を選択した -> 予約配列に追加
					this.isDisableCancel=true
					const sheet={num: index,type: this.ticketType};
					this.reserveSheet.push(sheet);
					this.reserveSheetNum.push(index)
					this.reserveSheetType.push(this.ticketType)
				}
			}else{
				const sheet={num: index,type: this.ticketType}
				this.reserveSheet.push(sheet)
				this.reserveSheetNum.push(index)
				this.reserveSheetType.push(this.ticketType)
				console.log(this.isDisableCancel)
			}
			this.selectedSheet=-1;
		},
		submitReserve:function(){
			const reserveForm=document.getElementById('reserveForm')
			reserveForm.submit()
		},
		getReserveType:function(type){
			console.log(type)
			switch(type){
			case '1':
				return '大人'
				break
			default:
				return 'その他'
			}
		},
		getReserveFee(type){
			switch(type){
			case '1':
				return 2000
				break
			default:
				return 0
			}
		},
	},
	computed:{
		cannotReserve:function(){
			if(this.reserveSheet.length>0){
				return false
			}else{
				return true
			}
		}
	}
})
</script>
</html>