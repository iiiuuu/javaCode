# javaCode
判断录入数据是否与历史数据重叠
现将历史数据进行遍历并将每个属性拆分为一个list，如果这组数据没有状态为1，表示如果录入数据可以通过
如果存在重叠，则返回false，如果存在历史数据中的一组与录入数据有重叠，则代表有重叠。
在判断区间时，先判断出这两组区间的最大边界值，然后从0开始累加，如果存在一个数，即在区间1中，也在区间2中，则代表区间重叠，如果需要加上左边界，可以找出最小的
的右边界值，然后进行判断。
这些是从项目中获得一种判断数据重复的方法，可能循环次数过多，如果有时间可以稍加修改，复杂度较高
